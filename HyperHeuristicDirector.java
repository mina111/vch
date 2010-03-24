
public class HyperHeuristicDirector {

	
    public void construct(HyperHeuristicBuilder hyperHeuristicBuilder,String functionName,String acceptanceMethodName,String[] lowLevelHeuristicsNames,String heuristicsSelectionName){
    	hyperHeuristicBuilder.buildFunction(functionName);
    	hyperHeuristicBuilder.buildAcceptanceMethod(acceptanceMethodName);
    	hyperHeuristicBuilder.LowLevelHeuristics(lowLevelHeuristicsNames);
    	hyperHeuristicBuilder.HeuristicsSelection(heuristicsSelectionName);
    }

}
