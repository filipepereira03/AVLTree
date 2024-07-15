public class ImplementacaoAVLTree {
    private Node root;

    private static class Node {
        int element;
        Node left;
        Node right;
        int height;

        Node(int element) {
            this.element = element;
            this.height = 1;
        }
    }

    /**
     * Adiciona um elemnto à árvore
     * @param element o elemento que é adicionado
     */
    public void add(int element) {
        root = add(root,element);
    }

    /**
     * Método auxiliar para adicionar recursivamente um elemento e balancear a árvore
     *
     * @param node nó atual
     * @param element o elemento que é adicionado
     * @return o nodo já balanceado
     */
    private Node add(Node node, int element) {
        if (node == null) { //Se for nulo, cria um novo nó
            return new Node(element);
        }

        if (element < node.element) { // Se o elemento for menor, adiciona a subarvore esquerda
            node.left = add(node.left, element);
        } else if (element > node.element) { // Se o elemento for maior, adiciona a direita
            node.right = add(node.right, element);
        } else {
            return node; // Se o elemento já existe, retorna o nó atual
        }
        // Atualiza a altura do nó
        node.height = 1 + Math.max(height(node.left), height(node.right));
        // Balanceia e retorna o nó
        return balance(node);
    }

    /**
     * Retorna o pai de um elemento na árvore
     * @param element elemento que o pai deve ser retornado
     * @return o valor do pai do elemento
     */
    public Integer getParent(int element) {
        return getParent(root, element, null);
    }

    /**
     * Método auxiliar que encontra o pai recursivamente
     * @param node nó atual
     * @param element elemento que o pai deve ser retornado
     * @param parent o nó pai atual
     * @return o valordo pai do elemento
     */
    private Integer getParent(Node node, int element, Node parent) {
        if (node == null) {
            return null;
        }

        if (node.element == element) {
            return parent == null ? null : parent.element;
        }
        // Procura recursivamente na esquerda e direita
        if (element < node.element) {
            return getParent(node.left, element, node);
        } else {
            return getParent(node.right, element, node);
        }
    }

    /**
     * Limpa a árvore
     */
    public void clear() {
    root = null;
    }

    /**
     * Verifica se o elemento está na árvore
     * @param element o elemento a ser verificado
     * @return true se o elemento estiver presente
     */
    public boolean contains(int element) {
        return contains(root, element);
    }

    /**
     * Método auxiliar pra verificar presença recursivamente
     * @param node nó atual
     * @param element o elemento que é verificado
     * @return true se o elemento estiver presente
     */
    private boolean contains(Node node, int element) {
        if (node == null) {
            return false;
        }

        if (element < node.element) {
            return contains(node.left, element);
        } else if (element > node.element) {
            return contains(node.right, element);
        } else {
            return true;
        }
    }

    /**
     * Altura da árvore
     * @return a altura da árvore
     */
    public int height() {
        return height(root);
    }

    /**
     * Método auxiliar que cálcula a altura de um nó
     * @param node o nó atual
     * @return altura do nó
     */
    private int height(Node node) {
        return node == null ? 0 : node.height; // Retorna a altura do nó ou 0 se for nulo
    }

    /**
     *
     * @return número de elementos na árvore
     */
    public int size() {
        return size(root);
    }

    /**
     * Método auxiliar pra contar número de elementos recursivamente
     * @param node nó atual
     * @return número de elemento no nó
     */
    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        // Retorna 1 mais o num de elementos nas sub da esquerda e direita
        return 1 + size(node.left) + size(node.right);
    }

    /**
     * Verifica se a árvore está vazia
     * @return true se a árvore estiver vazia
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Faz o caminhamento em ordem e imprime os elementos da árvore.
     */
    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    /**
     * Método auxiliar pra fazer o caminhamento em ordem recursivamente
     * @param node nó atual
     */
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left); // Caminha pela sub esquerda
            System.out.println(node.element + " ");
            inOrder(node.right); // Caminha pela direita
        }
    }

    /**
     * Balanceia a árvore depois de inserir um nó
     * @param node nó atual
     * @return nó balanceado
     */
    private Node balance(Node node) {
        int fatorBalanco = getFatorBalanco(node); // Calcula o fator de balanceamento
        // Se o nó estiver desbalanceado a esquerda
        if (fatorBalanco > 1) {
            // Se a sub esquerda estiver desbalanceada a direita
            if (getFatorBalanco(node.left) < 0) {
                node.left = leftRotate(node.left); // Faz a rotação a esquerda
            }
            return rightRotate(node); // Rotação a direita
        }

        if (fatorBalanco < -1) { // Se o nó estiver desbalanceado a direita
            // Se a sub direita estiver desbalanceada a esquerda
            if (getFatorBalanco(node.right) > 0) {
                node.right = rightRotate(node.right); // Faz a rotação a direita
            }
            return leftRotate(node); // Rotação a esquerda
        }

        return node; // Retorna balanceado o nó
    }

    /**
     * Calcula o fator de balanceamento de um nó
     * @param node nó atual
     * @return fator de balanceamento
     */
    private int getFatorBalanco(Node node) {
        // Retorna a diferença de altura entre as subs esquerdas e direitas
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    /**
     * Faz a rotação à direita de um nó
     * @param y nó desbalanceado
     * @return novo nó raiz pós rotação
     */
    private Node rightRotate(Node y) {
        Node x = y.left; // Define x como filho esquerdo de y
        Node T2 = x.right; // Guarda o filh direito de x
        // Faz a rotação
        x.right = y;
        y.left = T2;
        // Atualiza as alturas
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x; // Retorna o novo nó raiz
    }

    /**
     * Faz a rotação à esquerda de um nó
     * @param x nó desbalanceado
     * @return novo nó raiz pós rotação
     */
    private Node leftRotate(Node x) {
        Node y = x.right; // Define y como filho direito de x
        Node T2 = y.left; // Guarda o filho esquerdo de y
        // faz a rotação
        y.left = x;
        x.right = T2;
        // atualiza alturas
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y; // Retorna novo nó raiz
    }

    public void printTree() {
        if (root == null) {
            System.out.println("Árvore vazia!");
            return;
        }
        printTree(root, "", true);
    }

    private void printTree(Node node, String prefix, boolean isTail) {
        // verfica se o nó esquerdo n é nulo
        if (node.right != null) {
            // Chaa recursivamente para a sub esquerda, ajustando o prefixo
            printTree(node.right, prefix + (isTail ? "|   " : "    "), false);
        }
        // Imprime o prefixo seguido pelo elemento do nó atual
        System.out.println(prefix + (isTail ? "└── " : "┌── ") + node.element);
        // Mesma coisa só que pro nó direito
        if (node.left != null) {
            printTree(node.left, prefix + (isTail ? "    " : "|   "), true);
        }
    }
}
