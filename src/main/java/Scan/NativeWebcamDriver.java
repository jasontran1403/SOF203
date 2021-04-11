package Scan;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDriver;
import com.github.sarxos.webcam.WebcamException;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver for using the cross platform video capture library originally created by roxlu:
 * https://github.com/frankpapenmeier/video_capture
 *
 * @author Frank Papenmeier
 */
public class NativeWebcamDriver implements WebcamDriver {

	 /* READ ONLY! Populated by native layer!
	  *
	  * For each webcam found by native layer, the following arrays are incremented by 1 and populated:
	  * devicesIndex: Index used by native backend
	  * devicesName: Name of webcam
	  * Two-dimensional: Each webcam (1st dimension) supports multiple resolutions (2nd dimension):
	  * devicesCapabilityIndex: For each capability: index used by native backend
	  * devicesResolutionX: For each capability: resolution width
	  * devicesResolutionY: For each capability: resolution height
	  */
	private int[] devicesIndex; // index ->
	private String[] devicesName; // index -> name
	private int[][] devicesCapabilityIndex; // 1: index; 2: Capability index used by native backend
	private int[][] devicesResolutionX; // 1: index; 2: supported resolutions
	private int[][] devicesResolutionY; // 1: index; 2: supported resolutions

	public NativeWebcamDriver() {
	
		// Windows: Microsoft Media Foundation requires Windows 7 or higher (6.1 and above)
		if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0 && Double.valueOf(System.getProperty("os.version")) < 6.1) { 
			throw new WebcamException("NativeWebcamDriver requires Windows 7 or higher on Windows systems");
		}
	
		/* 
		 *  Load native backend
		 *  Problem: There is no reliable way to determine the bitness of the JVM across different JVM implementations
		 *  Workaround: Try to load 32 bit library first and if fails then try to load 64 bit library
		 */
		boolean libLoaded = false;
		try {
			System.loadLibrary("webcam-capture-driver-native-32");
			libLoaded = true;
		} catch (Exception e) {
		} catch (Error e) {
		}

		if (! libLoaded) {
			try {
				System.loadLibrary("webcam-capture-driver-native-64");
				libLoaded = true;
			} catch (Exception e) {
			} catch (Error e) {
			}
		}
		
		if (! libLoaded) {
			throw new WebcamException("NativeWebcamDriver: Failed to load JNI backend");
		}
	}

	@Override
	public List<WebcamDevice> getDevices() {
		List<WebcamDevice> devices = new ArrayList<WebcamDevice>();

		if (nativeGetDevices() == 0) { // success
			for (int i = 0; i < devicesIndex.length; i++) {
				devices.add(
						new NativeWebcamDevice(
							devicesIndex[i],
							devicesName[i],
							devicesCapabilityIndex[i],
							devicesResolutionX[i],
							devicesResolutionY[i]
						));
			}
		}


		return devices;
	}

	@Override
	public boolean isThreadSafe() {
		return false;
	}

	native int nativeGetDevices();
}