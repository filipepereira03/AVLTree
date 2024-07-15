public class MainAVL {

    public static void main(String[] args) {
        ImplementacaoAVLTree avl = new ImplementacaoAVLTree();

        System.out.println("Adicionando os números 1 a 9 na árvore AVL:");
        for (int i = 1; i <= 9; i++) {
            avl.add(i);
            System.out.println("Árvore apos adicionar " +i + ":");
            avl.printTree();
            System.out.println();
        }

        System.out.print("Caminhamento em ordem (AVL): ");
        avl.inOrder();

        // Apresentar a altura da árvore
        System.out.println("Altura da árvore (AVL): " + avl.height());

        // Limpar o conteúdo da árvore
        avl.clear();
        System.out.println("A árvore AVL foi esvaziada.");


        System.out.println("Adicionando os números 9 a 1 na árvore AVL:");
        for (int i = 9; i >= 1; i--) {
            avl.add(i);
            avl.printTree();
            System.out.println("Árvore após adicionar : " + i + ": ");
            System.out.println();
        }

        // Apresentar o conteúdo da árvore usando um caminhamento central
        System.out.print("Caminhamento em ordem após re-inserção (AVL): ");
        avl.inOrder();

        System.out.println("Visualização da árvore pós re-inserção (AVL): ");
        avl.printTree();
    }
}