package chapter04;

import java.util.Objects;

public class Rect {
	private int w;
	private int h;
	
	public Rect(int w,int h) {
		this.w=w;
		this.h=h;
	}
	
	// equals는 hashCode랑 같이 override 하기
	@Override
	public int hashCode() {
		return Objects.hash(h, w); // 넓이가 같다면 h*w
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rect other = (Rect) obj;
		return h == other.h && w == other.w; // 넓이가 같다면 h*w==other.w*other.h
	}

	@Override
	public String toString() {
		return "Rect [w=" + w + ", h=" + h + "]";
	}
	
	
}
