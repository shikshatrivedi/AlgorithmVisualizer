package model;

import java.util.List;

/**
 * Defines the contract for all algorithm implementations in the visualization system.
 * <p>
 * This interface is part of the <b>Strategy Design Pattern</b>, allowing the 
 * application to switch between different sorting, searching, and traversal 
 * algorithms dynamically at runtime without modifying the client code.
 * </p>
 */
public interface AlgorithmStrategy {

    /**
     * Executes the algorithm and records every state change as a visual step.
     *
     * @param array  The dataset to operate on.
     * @param target The target value (used only for searching algorithms, ignored for sorting).
     * @return A list of {@link AlgorithmStep} objects representing the frame-by-frame animation.
     */
    List<AlgorithmStep> generateSteps(int[] array, int target);
}