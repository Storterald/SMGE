package renderEngine

class RawModel(private var vaoID: Int, private var vertexCount: Int) {

    fun getVaoID(): Int {
        return vaoID
    }

    fun getVertexCount(): Int {
        return vertexCount
    }
}