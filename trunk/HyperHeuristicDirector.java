
public class HyperHeuristicDirector {
	HyperHeuristicBuilder hyperHeuristicBuilder;
	public HyperHeuristicDirector(HyperHeuristicBuilder hyperHeuristicBuilder){
		this.hyperHeuristicBuilder = hyperHeuristicBuilder;
	}

    public void construct(String functionName,String acceptanceMethodName,String[] lowLevelHeuristicsNames,String heuristicsSelectionName){
    	hyperHeuristicBuilder.buildFunction(functionName);
    	hyperHeuristicBuilder.buildAcceptanceMethod(acceptanceMethodName);
    	hyperHeuristicBuilder.LowLevelHeuristics(lowLevelHeuristicsNames);
    	hyperHeuristicBuilder.HeuristicsSelection(heuristicsSelectionName);
    }

}
