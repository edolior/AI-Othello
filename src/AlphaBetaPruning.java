import java.util.List;

public class AlphaBetaPruning implements ISolver {
    @Override
    public String getSolverName() {
        return "Alpha-Beta Pruning";
    }

    @Override
    public double solve
            (
                    IBoard board
            ) {
        Node root = new Node(board, Node.NodeType.MAX);
        return AlphaBetaPruningAlgorithm(root, -Double.MAX_VALUE, Double.MAX_VALUE);
    }


    private double AlphaBetaPruningAlgorithm
            (
                    Node node,
                    double p_alpha,
                    double p_beta
            ) {

        // Initialization
        double value;
        if (node.getNodeType() == Node.NodeType.MAX)
            value = -Double.MAX_VALUE;
        else
            value = Double.MAX_VALUE;
        double alpha = p_alpha;
        double beta = p_beta;

        // Explore Child Nodes
        List<Node> children = node.getNodeChildren();
        for (Node child : children) {
            if (child.isTerminalNode()) {
                return(child.getScore());
            } else {
                if (node.getNodeType() == Node.NodeType.MAX) {
                    value = Double.max(value, AlphaBetaPruningAlgorithm(child, alpha, beta));
                    alpha = Double.max(alpha,value);
                    if(alpha>=beta){
                        return alpha;
                    }
                } else {
                    value = Double.min(value, AlphaBetaPruningAlgorithm(child, alpha, beta));
                    beta = Double.min(beta,value);
                    if(alpha>=beta){
                        return beta;
                    }
                }

            }

        }
        return value;
    }
}