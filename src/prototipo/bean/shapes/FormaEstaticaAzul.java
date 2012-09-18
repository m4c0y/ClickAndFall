package prototipo.bean.shapes;

import prototipo.bean.shapes.interfaces.Azul;


public class FormaEstaticaAzul extends FormaEstatica implements Azul{

	public FormaEstaticaAzul(float positionX, float positionY, float width, float height) {
		super(positionX, positionY, width, height);
		this.setColor(0f, 0f, 1.0f);
	}
}
