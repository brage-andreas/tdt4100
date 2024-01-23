package assignment1;

public class Rectangle {
	public int x1, y1, x2, y2;

	public Rectangle(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public int getMinX() {
		return Math.min(this.x1, this.x2);
	}

	public int getMaxX() {
		return Math.max(this.x1, this.x2);
	}

	public int getMinY() {
		return Math.min(this.y1, this.y2);
	}

	public int getMaxY() {
		return Math.max(this.y1, this.y2);
	}

	public int getWidth() {
		return this.getMaxX() - this.getMinX();
	}

	public int getHeight() {
		return this.getMaxY() - this.getMinY();
	}

	public boolean isEmpty() {
		return this.getWidth() == 0 || this.getHeight() == 0;
	}

	public boolean contains(int x, int y) {
		if (this.isEmpty()) {
			return false;
		}

		boolean containsX = x >= this.getMinX() && x <= this.getMaxX();
		boolean containsY = y >= this.getMinY() && y <= this.getMaxY();

		return containsX && containsY;
	}

	public boolean contains(Rectangle rect) {
		boolean containsMin = this.contains(rect.getMinX(), rect.getMinY());
		boolean containsMax = this.contains(rect.getMaxX(), rect.getMaxY());

		return containsMin && containsMax;
	}

	public boolean add(int x, int y) {
		if (this.contains(x, y)) {
			return false;
		}

		if (x < this.getMinX()) {
			if (this.x1 < this.x2) {
				this.x1 = x;
			} else {
				this.x2 = x;
			}
		} else if (x > this.getMaxX()) {
			if (this.x1 > this.x2) {
				this.x1 = x;
			} else {
				this.x2 = x;
			}
		}

		if (y < this.getMinY()) {
			if (this.y1 < this.y2) {
				this.y1 = y;
			} else {
				this.y2 = y;
			}
		} else if (y > this.getMaxY()) {
			if (this.y1 > this.y2) {
				this.y1 = y;
			} else {
				this.y2 = y;
			}
		}

		return true;
	}

	public boolean add(Rectangle rect) {
		if (rect.isEmpty()) {
			return false;
		}

		if (this.contains(rect)) {
			return false;
		}

		this.add(rect.getMinX(), rect.getMinY());
		this.add(rect.getMaxX(), rect.getMaxY());

		return true;
	}

	public Rectangle union(Rectangle rect) {
		Rectangle union = new Rectangle(this.getMinX(), this.getMinY(), this.getMaxX(), this.getMaxY());
		union.add(rect);
		return union;
	}
}
