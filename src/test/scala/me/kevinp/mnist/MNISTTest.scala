package me.kevinp.mnist

import org.scalatest.FunSuite

class MNISTTest extends FunSuite {

  test("correct training images size") {
    assert(MNIST.trainingImages.size == 60000)
  }

  test("correct training labels size") {
    assert(MNIST.trainingLabels.size == 60000)
  }

  test("correct testing images size") {
    assert(MNIST.testingImages.size == 10000)
  }

  test("correct testing labels size") {
    assert(MNIST.testingLabels.size == 10000)
  }

}
