package A04_TraverseTree;

import java.util.*;


public class Wörterbuch {

    /**
     * Wurzel des Baums (Startknoten)
     */
    private Wort root;

    public Wort getRoot() {
        return root;
    }

    /**
     * Zählt alle Wörter des Teilbaums ab einem bestimmten Wort
     *
     * @param w Wort
     * @return Zahl der Wörter (=Anzahl der Elemente)
     */
    public int countWordsInSubTree(Wort w) {

        // kein  Objekt im Baum
        if (w == null) {
            return 0;
        }
        // Linker und rechter Teil Gleich Null - kein Kinder mehr vorhanden- Abbruchbedingung für Rekursion
        if (w.getRight() == null && w.getLeft() == null) {
            return 1;
        }
        int right = 0;
        if (w.getRight() != null) {
            right = countWordsInSubTree(w.getRight());
        } //Rechter Teilbaum
        int left = 0;
        if (w.getLeft() != null) {
            left = countWordsInSubTree(w.getLeft());
        } // Linker Teilbaum

        return right + left + 1; //Summe Kinder (Links & Rechts)  + Elternelement
    }

    /**
     * Liefert die Menge aller Wörter retour, die ein spezifisches Präfix haben.
     *
     * @param prefix Wörter müssen diesen Präfix haben
     * @return Menge aller zutreffenden Wörter
     */
    public Set<String> getWordsWithPrefix(String prefix) {

        Wort wort = getRoot();
        Set<String> list = new HashSet<>();

     list = findPrefix(root,prefix);


        //if (wort.getWort().startsWith(prefix)) {
        //  list.add(wort.getWort());
        //}


       /* while (wort.getLeft() != null && wort.getRight() != null) {

            //  int val = wort.getWort().compareTo(prefix);
            int val = prefix.compareTo(wort.getWort());

            if (val < 0 && wort.getLeft() != null)
                wort = wort.getLeft();
            else if (val > 0 && wort.getRight() != null)
                wort = wort.getRight();
            else
                wort = null;

            if (wort.getWort().startsWith(prefix)) {
                list.add(wort.getWort());
            }
        }
*/
        return list;

    }

    private Set<String> findPrefix(Wort current, String s) {
        Set<String> list = new HashSet<>();

        if (current == null) {
            return null;
        }

        int compare = s.compareTo(current.getWort());
        if (compare < 0) {    // Linker Teilbaum
            if (current.getWort().startsWith(s)) {
                list.add(current.getWort());
            }
            find(current.getLeft(), s);
        } else {                // Rechter Teilbaum
            if (current.getWort().startsWith(s)) {
                list.add(current.getWort());
            }
            find(current.getRight(), s);
        }

        return list;
    }


    /**
     * Neues Wort hinzufügen
     *
     * @param wort Hinzuzufügendes Wort
     */
    public void add(String wort) {
        Wort neu = new Wort(wort);
        if (root == null) {            // Fall 1: Baum ist leer
            root = neu;
            return;
        }
        Wort w = root;                // Fall 2: Baum ist nicht leer
        while (true) {
            int vgl = wort.compareTo(w.getWort());
            if (vgl < 0) {            // Neues Wort ist lexikographisch kleiner
                if (w.getLeft() == null) {
                    w.setLeft(neu);
                    neu.setParent(w);
                    return;
                }
                w = w.getLeft();
            } else if (vgl > 0) {        // Neues Wort ist lexikographisch größer
                if (w.getRight() == null) {
                    w.setRight(neu);
                    neu.setParent(w);
                    return;
                }
                w = w.getRight();
            } else {                    // Neues Wort ist lexikographisch gleich
                return;
            }
        }
    }

    public Wort find(String s) {
        return find(root, s);
    }

    private Wort find(Wort current, String s) {
        if (current == null) {
            return null;
        }
        int vgl = s.compareTo(current.getWort());
        if (vgl == 0) {        // Gefunden
            return current;
        } else if (vgl < 0) {    // Links
            return find(current.getLeft(), s);
        } else {                // Rechts
            return find(current.getRight(), s);
        }
    }

}
