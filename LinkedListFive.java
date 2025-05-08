//Manuel Padilla - 10426597
/*
EXERCÍCIO 5. Remover Duplicatas
Dada uma lista ligada de inteiros com possíveis elementos repetidos,
remova os valores duplicados mantendo apenas a primeira
ocorrência. 
*/

//aqui tmabém coloquei "MainEx5" no inicio do para testar o funcionamento da lista ligada.
class MainEx5 {
    public static void main(String[] args) {
        LinkedListFive<Integer> lista = new LinkedListFive<>();
        //criei uma lista ligada de exemplo do tipo integer.
        lista.insertSorted(10);
        lista.insertSorted(20);
        lista.insertSorted(20);
        lista.insertSorted(30);
        lista.insertSorted(30);
        lista.insertSorted(40);
        lista.insertSorted(40);
        lista.insertSorted(40);

        //valores na lista de forma ordenada, inclusive alguns valores duplicados
        System.out.print("Antes de remover duplicatas: ");
        lista.printList();

        lista.removeDuplicates();

        //remove os elementos duplicados da lista.
        System.out.print("Após remover duplicatas: ");
        lista.printList();
    }
}


//Defini uma lista ligada genérica
//onde T é um tipo qualquer que implementa Comparable (para permitir ordenação).

public class LinkedListFive<T extends Comparable<T>> {
    //aqui coloquei o nó da lista para não criar outro arquivo
    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    private Node<T> head;

    //Exceção customizada usada quando se tenta operar em uma lista vazia.
    public static class EmptyListException extends RuntimeException {
        public EmptyListException(String message) { super(message); }
    }


    //Cria um novo nó com o valor a ser inserido.
    public void insertSorted(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null || head.data.compareTo(value) > 0) {
            newNode.next = head;
            head = newNode;
            return;
        }

        //Caso a lista esteja vazia 
        //o primeiro nó é colocado no início da lista
        Node<T> current = head;
        while (current.next != null && current.next.data.compareTo(value) < 0) {
            current = current.next;
        }
        //aqui insere o novo nó entre os elementos apropriados.
        newNode.next = current.next;
        current.next = newNode;
    }

    // método para remover duplicatas
    public void removeDuplicates() {
        if (head == null) throw new EmptyListException("Lista vazia!");

        //Lança exceção se a lista estiver vazia.
        Node<T> current = head;
        while (current != null && current.next != null) {
            if (current.data.equals(current.next.data)) {
                current.next = current.next.next; // remove duplicata
            } else {
                current = current.next;
            }
        }
    }

    public void printList() {
        if (head == null) throw new EmptyListException("Lista vazia!");
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("Null");
    }
}
