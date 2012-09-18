package prototipo.bean.fases;

import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.entity.shape.IShape;

import prototipo.bean.shapes.interfaces.Azul;
import prototipo.bean.shapes.interfaces.Verde;
import prototipo.bean.shapes.interfaces.Vermelho;

import android.graphics.PointF;

public class Fase {

	private List<IShape> shapes = new ArrayList<IShape>();
	
	private List<IShape> redShapes = new ArrayList<IShape>();
	private List<IShape> greenShapes = new ArrayList<IShape>();
	private List<IShape> blueShapes = new ArrayList<IShape>();
	
	private List<PointF> points = new ArrayList<PointF>();
	
	public Fase(List<IShape> shapes) {
		
		this.setShapes(shapes);
				
		for (IShape shape : shapes) {
			if(shape instanceof Verde){
				greenShapes.add(shape);
				points.add(new PointF(shape.getX(), shape.getY()));
			}
			else if(shape instanceof Vermelho) {
				redShapes.add(shape);
			}
			else if(shape instanceof Azul) {
				blueShapes.add(shape);
			}
		}
	}
	
	public List<IShape> getShapes() {
		return shapes;
	}
	public void setShapes(List<IShape> shapes) {
		this.shapes = shapes;
	}
	public List<IShape> getRedShapes() {
		return redShapes;
	}
	public List<IShape> getGreenShapes() {
		return greenShapes;
	}
	public List<IShape> getBlueShapes() {
		return blueShapes;
	}
	public List<PointF> getPoints() {
		return points;
	}
}
