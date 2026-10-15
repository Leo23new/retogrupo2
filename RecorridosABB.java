public class RecorridosABB {

    static class Nodo {
        int matricula;
        Nodo izq, der;
        Nodo(int m) { matricula = m; }
    }

    // Ya viene hecho en la plantilla: ignora repetidos
    static Nodo insertar(Nodo nodo, int m) {
        if (nodo == null) return new Nodo(m);
        if (m < nodo.matricula)      nodo.izq = insertar(nodo.izq, m);
        else if (m > nodo.matricula) nodo.der = insertar(nodo.der, m);
        return nodo; // si es igual, se ignora
    }

    // PREORDEN: visita -> izquierda -> derecha
    static void preorden(Nodo nodo, String sangria, StringBuilder salida) {
        if (nodo == null) {
            System.out.println(sangria + "null -> regresa");
            return;
        }
        System.out.println(sangria + "entra " + nodo.matricula);
        System.out.println(sangria + "VISITA " + nodo.matricula);
        salida.append(nodo.matricula).append(' ');
        preorden(nodo.izq, sangria + "  ", salida);
        preorden(nodo.der, sangria + "  ", salida);
    }

    // INORDEN: izquierda -> visita -> derecha
    static void inorden(Nodo nodo, String sangria, StringBuilder salida) {
        if (nodo == null) {
            System.out.println(sangria + "null -> regresa");
            return;
        }
        System.out.println(sangria + "entra " + nodo.matricula);
        inorden(nodo.izq, sangria + "  ", salida);
        System.out.println(sangria + "VISITA " + nodo.matricula);
        salida.append(nodo.matricula).append(' ');
        inorden(nodo.der, sangria + "  ", salida);
    }

    // POSORDEN: izquierda -> derecha -> visita
    static void posorden(Nodo nodo, String sangria, StringBuilder salida) {
        if (nodo == null) {
            System.out.println(sangria + "null -> regresa");
            return;
        }
        System.out.println(sangria + "entra " + nodo.matricula);
        posorden(nodo.izq, sangria + "  ", salida);
        posorden(nodo.der, sangria + "  ", salida);
        System.out.println(sangria + "VISITA " + nodo.matricula);
        salida.append(nodo.matricula).append(' ');
    }

    // EXTRA: altura en aristas (hoja = 0, vacío = -1)
    static int altura(Nodo nodo) {
        if (nodo == null) return -1;
        return 1 + Math.max(altura(nodo.izq), altura(nodo.der));
    }

    // ---------- Utilidades de prueba ----------
    static Nodo construir(int... matriculas) {
        Nodo raiz = null;
        for (int m : matriculas) raiz = insertar(raiz, m);
        return raiz;
    }

    static void probar(String titulo, Nodo raiz) {
        System.out.println("\n==================== " + titulo + " ====================");

        StringBuilder pre = new StringBuilder();
        System.out.println("--- PREORDEN ---");
        preorden(raiz, "", pre);

        StringBuilder in = new StringBuilder();
        System.out.println("--- INORDEN ---");
        inorden(raiz, "", in);

        StringBuilder pos = new StringBuilder();
        System.out.println("--- POSORDEN ---");
        posorden(raiz, "", pos);

        System.out.println("\nPreorden: " + pre.toString().trim());
        System.out.println("Inorden:  " + in.toString().trim());
        System.out.println("Posorden: " + pos.toString().trim());
        System.out.println("Altura:   " + altura(raiz));
    }

    public static void main(String[] args) {
        probar("PARALELO SW-N3-A", construir(45, 25, 70, 10, 30, 60, 90, 27, 65));
        probar("ARBOL VACIO", construir());
        probar("UN SOLO NODO (50)", construir(50));
        probar("CADENA (10, 20, 30, 40)", construir(10, 20, 30, 40));
        probar("REPETIDO (20, 30, 30, 10)", construir(20, 30, 30, 10));
    }
}