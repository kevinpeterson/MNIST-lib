package me.kevinp.mnist

import java.io.{BufferedInputStream, ByteArrayOutputStream, InputStream}
import scala.Array

class Image(val data: Array[Array[Int]]) {

  def display() = {
    val bar = "+" + new String(Array.fill(28)('-')) + "+"

    println(bar)
    for (i <- 0 until data.size) {
      print("|")
      for (j <- 0 until data(i).size) {
        val v = data(i)(j)
        val c = v match {
          case x if x < 50 => ' '
          case x if 50 until 100 contains x => '.'
          case x if 100 until 200 contains x => 'x'
          case x if x > 200 => 'X'
          case _ => '?'
        }
        print(c)
      }
      println("|")
    }
    println(bar)
  }

}

class IdentifiedImage(val digit: Int, val image: Image)

object MNIST {

  private def readLabels(file: String) = readBinaryFile(file).drop(8).map(_.toInt)

  val trainingLabels: Array[Int] = readLabels("mnist/train-labels-idx1-ubyte")
  val testingLabels: Array[Int] = readLabels("mnist/t10k-labels-idx1-ubyte")

  val trainingImages = readImages("mnist/train-images-idx3-ubyte")
  val testingImages = readImages("mnist/t10k-images-idx3-ubyte")

  private def readImages(file: String): Array[Image] = {
    val byteArray = readBinaryFile(file)

    byteArray.slice(16, byteArray.size).grouped(784).foldLeft(Array[Image]())((array, d) => {
      val digit: Array[Array[Int]] = Array.ofDim(28, 28)
      var xpos: Int = 0
      var ypos: Int = 0

      d.foreach(x => {
        val p = (x & 0xFF)

        digit(ypos)(xpos) = p

        if(xpos == 27) {
          xpos = 0
          ypos += 1
        } else {
          xpos += 1
        }

      })

      val image = new Image(digit)

      array :+ image
    })
  }

  private def readBinaryFile(fileName: String):Array[Byte] = {
    val input = Thread.currentThread.getContextClassLoader.getResourceAsStream(fileName)
    
    val fos = new ByteArrayOutputStream(65535)
    val bis = new BufferedInputStream(input)
    val buf = new Array[Byte](1024)

    Stream.continually(bis.read(buf))
      .takeWhile(_ != -1)
      .foreach(fos.write(buf, 0, _))

    fos.toByteArray
  }

  val trainingSet: Array[IdentifiedImage] = {
    trainingImages.zip(trainingLabels).map(zip => new IdentifiedImage(zip._2, zip._1))
  }

  val testingSet: Array[IdentifiedImage] = {
    testingImages.zip(testingLabels).map(zip => new IdentifiedImage(zip._2, zip._1))
  }

}