package model;

public class AlgorithmStep {
    private int[] arraySnapshot;
    private int[] highlightedIndices;
    private String description;
    private String status;
    private int complexityLine;

    // --- CONSTRUCTOR 1: The "Simple" Fix (Solves your 16 errors) ---
    // This allows your existing algorithms to work without changing them.
    public AlgorithmStep(int[] array, int[] highlights) {
        this(array, highlights, "Processing...", "Active", 0); 
    }

    // --- CONSTRUCTOR 2: The "Advanced" Version ---
    // This keeps the "intense" features available for later.
    public AlgorithmStep(int[] array, int[] highlights, String description, String status, int complexityLine) {
        this.arraySnapshot = array != null ? array.clone() : new int[0];
        this.highlightedIndices = highlights;
        this.description = description;
        this.status = status;
        this.complexityLine = complexityLine;
    }

    // Getters
    public int[] getArraySnapshot() {
        return arraySnapshot;
    }

    public int[] getHighlightedIndices() {
        return highlightedIndices;
    }
    
    public String getDescription() {
        return description;
    }
}