package me.kevinp.mnist;

public class MNISTJ {

    private static MNIST$ MNIST = MNIST$.MODULE$;

    public static IdentifiedImage[] getTrainingSet() {
        return MNIST.trainingSet();
    }

    public static IdentifiedImage[] getTestingSet() {
        return MNIST.testingSet();
    }

}
