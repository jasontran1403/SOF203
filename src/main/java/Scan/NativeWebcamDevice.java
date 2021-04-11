package Scan;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Hashtable;

/**
 * Driver for using the cross platform video capture library originally created by roxlu:
 * https://github.com/frankpapenmeier/video_capture
 *
 * @author Frank Papenmeier
 */
public class NativeWebcamDevice implements WebcamDevice, WebcamDevice.BufferAccess {

	private long ptrNativeCapture; // USED IN NATIVE BACKEND - DO NOT EDIT! - long holding the pointer to native Capture class

	private int indexNative;
	private String name;
	private Dimension currentResolution;
	private Dimension[] supportedResolutions;
	private Hashtable<String,Integer> mapResolutionToCapabilityIndex;

	private boolean isOpen = false;
	private ByteBuffer byteBufferARGB;

	public NativeWebcamDevice(int indexNative, String name, int[] capabilityIndex, int[] resolutionX, int[] resolutionY) {
		this.indexNative = indexNative;
		this.name = name;

		supportedResolutions = new Dimension[capabilityIndex.length];
		mapResolutionToCapabilityIndex = new Hashtable<String,Integer>();
		for (int i = 0; i < capabilityIndex.length; i++) {
			supportedResolutions[i] = new Dimension(resolutionX[i],resolutionY[i]);
			mapResolutionToCapabilityIndex.put(resolutionX[i]+"x"+resolutionY[i],capabilityIndex[i]);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Dimension[] getResolutions() {
		return supportedResolutions;
	}

	@Override
	public Dimension getResolution() {
		return currentResolution;
	}

	@Override
	public void setResolution(Dimension resolution) {
		if (! mapResolutionToCapabilityIndex.containsKey(resolution.width+"x"+resolution.height)) {
			throw new WebcamException("Requested resolution is not supported by webcam: "+resolution.width+"x"+resolution.height);
		}

		currentResolution = resolution;
	}

	@Override
	public BufferedImage getImage() {
		if (byteBufferARGB != null) {
			IntBuffer argbImageInt = byteBufferARGB.asIntBuffer();
			final int[] pixels = new int[argbImageInt.capacity()];
			argbImageInt.get(pixels);
			BufferedImage image = new BufferedImage( currentResolution.width, currentResolution.height, BufferedImage.TYPE_INT_ARGB );
			final int[] a = ( (DataBufferInt) image.getRaster().getDataBuffer() ).getData();
			System.arraycopy(pixels, 0, a, 0, pixels.length);
			return image;
		}

		return null;
	}

	@Override
	public void open() {
		if (isOpen) {
			throw new WebcamException("Error opening webcam: already opened");
		}

		if (currentResolution == null) {
			throw new WebcamException("Error opening webcam: resolution was not set");
		}

		Integer indexCapability = mapResolutionToCapabilityIndex.get(currentResolution.width+"x"+currentResolution.height);

		if (indexCapability == null) {
			throw new WebcamException("Error opening webcam: capability for resolution not found");
		}

		byteBufferARGB = ByteBuffer.allocateDirect(currentResolution.width*currentResolution.height*4);
		byteBufferARGB.order(ByteOrder.nativeOrder());

		if (nativeOpen(indexNative, indexCapability.intValue(), byteBufferARGB) != 0) {
			throw new WebcamException("Error at native layer while opening webcam");
		}

		// Wait for first image to be captured - max wait: 10 seconds
		long timeWaitForImageStop = System.currentTimeMillis() + 10000L;
		boolean imageCaptured = false;
		while (System.currentTimeMillis() < timeWaitForImageStop && ! imageCaptured) {
			// Has ByteBuffer been filled with pixel data already?
			ByteBuffer byteBufferARGBReadOnly = byteBufferARGB.asReadOnlyBuffer(); // asReadOnly: new position marker that does not affect original byteBufferARGB
			while (byteBufferARGBReadOnly.hasRemaining()) {
				if (byteBufferARGBReadOnly.get() != 0) {
					imageCaptured = true;
					break;
				}
			}

			try {
				if (! imageCaptured) {
					Thread.sleep(100);
				}
			} catch (InterruptedException exc) {
			}
		}

		isOpen = true;
	}

	@Override
	public void close() {
		if (nativeClose() != 0) {
			throw new WebcamException("Error at native layer while closing webcam");
		}

		isOpen = false;
		byteBufferARGB = null;
	}

	@Override
	public void dispose() {
		if (isOpen) {
			close();
		}
	}

	@Override
	public boolean isOpen() {
		return isOpen;
	}

	@Override
	public ByteBuffer getImageBytes() {
		ByteBuffer byteBufferARGBReadOnly = byteBufferARGB.asReadOnlyBuffer();
		byteBufferARGBReadOnly.order(byteBufferARGB.order());

		return byteBufferARGBReadOnly;
	}

	@Override
	public void getImageBytes(ByteBuffer target) {
		if (byteBufferARGB == null) {
			throw new WebcamException("Error getting image bytes because internal ByteBuffer was null");
		}

		target.put(byteBufferARGB.asReadOnlyBuffer()); // asReadOnly: new position marker that does not affect original byteBufferARGB
		target.order(byteBufferARGB.order());
	}

	native int nativeOpen(int indexDevice, int indexCapability, ByteBuffer byteBufferARGB);
	native int nativeClose();


}