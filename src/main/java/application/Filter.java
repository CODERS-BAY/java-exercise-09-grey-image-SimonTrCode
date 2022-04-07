package application;

import java.awt.Color;

import picture.Picture;

public class Filter {
	private Picture picture;

	public Filter(Picture picture) {
		this.picture = picture;
	}

	/**
	 * The input picture should be converted to a grey scale. To achieve a grey
	 * image we need to sum up the red, green and blue value and divide it by 3.
	 * 
	 * @return converted picture
	 */
	public Picture greyScaleFilter() {
		Color  color;
		for (int i = 0; i < this.picture.width() ; i++) {
			for (int j = 0; j < this.picture.height(); j++) {

				color = this.picture.get(j,i);
				int blue =color.getBlue();
				int red = color.getRed();
				int green = color.getGreen();
				int mittelwert = (blue + red + green)/3;
				Color color1 = new Color(mittelwert,mittelwert,mittelwert);
				this.picture.set(j,i,color1);
			}
		}

		return picture;
	}

	/**
	 * As we have a range until 255 inclusive getting the inverted image is easy.
	 * Just subtract the color value from 255.
	 * 
	 * @return converted picture
	 */
	public Picture revertColorFilter() {
		Color  color;
		for (int i = 0; i < this.picture.width() ; i++) {
			for (int j = 0; j < this.picture.height(); j++) {

				color = this.picture.get(j,i);
				Color color1 = new Color(255-color.getRed(),255-color.getGreen(),255-color.getBlue());
				this.picture.set(j,i,color1);
			}
		}
		return picture;
	}

	//@formatter:off
	/**
	 * A sepia filter is a little more difficult to calculate.
	 * to get the
	 * 
	 * sepia red: (current red * 0.393) + (current green * 0.769) + (current blue * 0.189)
	 * sepia green: (current red * 0.349) + (current green * 0.686) + (current blue * 0.168)
	 * sepia blue: (current red * 0.292) + (current green * 0.534) + (current blue * 0.131)
	 * 
	 * the new value is then Math.min(sepia red, 255) and so on.
	 * 
	 * @return converted picture
	 */
	//@formatter:on

	public Picture sepiaFilter() {
		Color  color;
		for (int i = 0; i < this.picture.width() ; i++) {
			for (int j = 0; j < this.picture.height(); j++) {

				color = this.picture.get(j,i);
				int blue =color.getBlue();
				int red = color.getRed();
				int green = color.getGreen();
//				sepia red: (current red * 0.393) + (current green * 0.769) + (current blue * 0.189)
				double sepiaRed = (red * 0.393)+ (green * 0.769) + (blue * 0.189);
				double sepiaGreen = (red * 0.349) + (green * 0.686) + (blue * 0.168);
				double sepiaBlue = (red * 0.292) + (green * 0.534) + (blue * 0.131);
				Color color1 = new Color((int)Math.min(sepiaRed, 255),(int)Math.min(sepiaGreen, 255),(int)Math.min(sepiaBlue, 255));
				this.picture.set(j,i,color1);
			}
		}
		return picture;
	}
}
