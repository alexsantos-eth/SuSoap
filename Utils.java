import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
  // ============= UTILIDADES EN CONSOLA ========================
  // IMPRIMIR EN CONSOLA
  public static void print(String msg) {
    System.out.print(msg);
  }

  // IMPRIMIR TEXTO CENTRADO
  public static void printCenter(String text, String sperator) {
    // CALCULAR EL ESPACIO
    int spaces = Math.round((67 - text.trim().length()) / 2);

    // MOSTRAR LA MITAD DE ESPACIOS
    System.out.print("\n");
    System.out.print(repeatString(sperator, spaces - 1));

    // MOSTRAR EL TEXTO
    System.out.print(" " + text.trim() + " ");

    // MOSTRAR LA OTRA MITAD
    System.out.print(repeatString(sperator, spaces - 1));
  }

  // MOSTRAR CABECERA DE MENUS
  public static void printMenu(String title, int warning) {
    clearScreen(warning);
    System.out.println("");
    printCenter(title, " ");
    System.out.print("\n-------------------------------------------------------------------\n");
  }

  // IMPRIMIR BANNER
  public static void printBanner() {
    System.out.print(
        "    MP''''''`MM          MP''''''`MM\n    M  mmmmm..M          M  mmmmm..M\n    M.      `YM dP    dP M.      `YM .d8888b. .d8888b. 88d888b.\n    MMMMMMM.  M 88    88 MMMMMMM.  M 88'  `88 88'  `88 88'  `88\n    M. .MMM'  M 88.  .88 M. .MMM'  M 88.  .88 88.  .88 88.  .88\n    Mb.     .dM `88888P' Mb.     .dM `88888P' `88888P8 88Y888P'\n    MMMMMMMMMMM          MMMMMMMMMMM                   88\n                                                       dP  ");
  }

  // DAR FORMATO A LOS MENUS
  public static void clearScreen(int warning) {
    // EJECUTAR COMANDO CLS O CLEAR EN MAC/LINUX
    try {
      if (System.getProperty("os.name").contains("Windows"))
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      else
        Runtime.getRuntime().exec("clear");
    }
    // MOSTRAR LA EXCEPCION
    catch (IOException | InterruptedException ex) {
      System.out.println(ex);
    }

    // LUEGO DE LIMPIAR MOSTRAR EL BANNER Y ADVERTENSIAS SI LAS HAY
    printBanner();
    showWarning(warning);
  }

  // MENSAJES DE ERROR
  public static void showWarning(int index) {
    // SELECCIONAR UNICA ALERTA EN STRINGS()
    Strings warnings = new Strings();
    String warning = warnings.getWarning(index);
    System.out.println("");

    // SI ES 0 MOSTRAR LINEA RECTA
    if (index == 0)
      System.out.print("\n" + repeatString("=", 67));

    // SINO MOSTRAR LA ALERTA
    else
      printCenter(warning, "=");
  }

  // ============= UTILIDADES PARA EL SCANNER =============

  // OPTENER OPCION DE CUALQUIER MENU COMO NUMERO
  public static int getOption(String name, Scanner input) {
    // FORMATO PARA INGRESAR
    System.out.print("\n" + name + " => ");

    // DETECTAR SI INGRESO CARACTER DIFERENTE DE ENTERO
    try {
      return input.nextInt();
    }

    // CONTINUAR LEYENDO Y RETORNAR ERROR 101
    catch (InputMismatchException ex) {
      input.nextLine();
      return 101;
    }
  }

  // OPTENER TEXTO DE ENTRADA COMO STRING
  public static String getOptionStr(String name, Scanner input) {
    System.out.print("\n" + name + " => ");
    return input.next();
  }

  // ============= UTILIDADES PARA STRINGS =============
  // GENEREAR LETRA RANDOM
  public static char randomLetter() {
    char[] letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    // RETORNAR LETRA RANDOM DE 0 A 26*2
    return letters[(int) (Math.random() * (letters.length))];
  }

  // REPETIR STRING N VECES
  public static String repeatString(String str, int n) {
    // DECLARAR SALIDA
    String out = "";

    // SUMARLE LA SALIDA N VECES
    for (int count = 0; count < n; count++)
      out += str;

    // RETORNAR SALIDA
    return out;
  }

  // VERIFICAR SI LA LONGITUD ESTA ENTRE 5 Y 10
  public static boolean wordOverflow(String word) {
    return (word.length() >= 5 && word.length() <= 10);
  }

  // ============= UTILIDADES PARA ENTEROS =============
  // OBTENER NUMERO ALEATORIO ENTRE MIN(INCLUIDO) Y MAX(INCLUIDO)
  public static int random(int min, int max) {
    return (int) (Math.random() * ((max - min) + 1)) + min;
  }

  // GENERAR UNA LISTA DE NUMEROS ALEATORIOS NO REPETIDOS
  public static int[] randomList(int n) {
    // DECLARAR ARRAY DE SALIDA E INICIALIZAR CONTADOR
    int[] out = new int[n];
    int listIndex = 0;

    // RECORRER EL ARRAY NUEVO
    while (listIndex < n) {
      // GENERAR NUMERO ALEATORIO ENTRE 0 Y N
      int randomNumber = (int) (Math.random() * (n + 1));
      boolean breaks = false;

      // CAMBIAR BREAKS SI SE REPITE UN NUMERO
      for (int listCount = 0; listCount < n; listCount++)
        if (randomNumber == out[listCount])
          breaks = true;

      // SINO SE REPITIO NINGUNO ASIGNAR A OUT
      if (!breaks) {
        out[listIndex] = randomNumber;
        listIndex++;
      }
    }

    // REDUCIR LA CANTIDAD DE AUMENTO POR EL RANDOM
    for (int finalCount = 0; finalCount < n; finalCount++)
      out[finalCount] = out[finalCount] - 1;

    // RETORNAR LA LISTA ALEATORIA NO REPETIDA
    return out;
  }

  // INVERTIR LISTA DE ENTEROS
  public static int[] reverse(int orderList[]) {
    // DECLARAR LISTA INVERTIDA
    int[] inverse = new int[orderList.length];
    int inverseCount = orderList.length;

    // ASIGNAR EL VALOR DE EXTREM0 A EXTREMO
    for (int listIndex = 0; listIndex < orderList.length; listIndex++)
      inverse[inverseCount - listIndex - 1] = orderList[listIndex];

    // RETORNAR LISTA INVERSA
    return inverse;
  }

  // ============= UTILIDADES PARA BANCO DE PALABRAS =============
  // VERIFICIAR SI EL BANCO ESTA VACIO
  public static boolean isEmpty(String[] words) {
    return words.length == 0;
  }

  // VERIFICAR SI SE REPITEN LAS PALABRAS Y DONDE
  public static int isWordRepeats(String word, int limit, String[] words) {
    // VALOR POR DEFECTO
    int out = -1;

    // RECORRER LAS PALABRAS HASTA LENGTH O LIMITE
    for (int wordIndex = 0; wordIndex < (limit == 0 ? words.length : limit); wordIndex++)
      // SI EL BANCO CONTIENE LA PALABRA ASIGNAR EL INDEX Y SALIR
      if (words[wordIndex].equals(word)) {
        out = wordIndex;
        break;
      }

    // RETORNAR LA POSICION SI SE ENCONTRO
    return out;
  }

  // BUSCAR PALABRA EN EL BANCO
  public static String searchWord(int warning, String name, Scanner input) {
    // MOSTRAR MENU PARA BUSCAR
    printMenu("BUSCAR PALABRA EN EL BANCO DE PALABRAS", warning);
    String searchedWord = getOptionStr(name, input);

    // VERIFICAR LA LONGITUD
    if (wordOverflow(searchedWord)) {
      warning = 0;
      return searchedWord;
    }

    // SINO MOSTRAR ERROR 2 Y DEVOLVER CADENA VACIA
    else {
      warning = 2;
      return "";
    }
  }

  // ============= UTILIDADES PARA MATRIZ =============
  // OBTENER LAS PALABRAS CON LONGITUD MAXIMA
  public static int[] maxLengthWord(String[] words, int matrixSize) {
    // DECLARAR ARRAY DE PALABRAS
    int searched[] = new int[words.length];
    int extCount = 0;

    // ASIGNAR LAS PALABRAS MAS GRANDES A ARRAY SI SU LONGITUD ES MATRIXSIZE
    for (int wordsIndex = 0; wordsIndex < words.length; wordsIndex++)
      if (words[wordsIndex].length() == matrixSize) {
        searched[extCount] = wordsIndex;
        extCount++;
      }

    // REDUCIR LA LONGITUD DEL ARRAY RESULTANTE
    int out[] = new int[extCount];
    for (int newCount = 0; newCount < extCount; newCount++)
      out[newCount] = searched[newCount];

    // RETORNAR EL ARRAY REDUCIDO
    return out;
  }

  // RELLENAR MATRIZ CON PALABRAS ALEATORIAS
  public static void fillMatrix(char[][] words) {
    // RECORRER FILAS
    for (int row = 0; row < words.length; row++)
      // RECORRER COLUMNAS
      for (int col = 0; col < words.length; col++)
        // ASIGNAR LETRA RANDOM
        words[row][col] = randomLetter();
  }

  // INSERTAR PALABRA EN MATRIZ
  public static void insertonMatrix(int mode, int where, int start, String word, char[][] words) {
    // CALCULAR ESPACIO
    int space = (words.length - word.length());
    int wordCount = -1;

    // MODO 0 HORIZONATAL , MODO 1 VERTICAL
    for (int wordsIndex = random(start, space); wordsIndex < words.length; wordsIndex++) {
      wordCount++;
      if (wordCount < word.length())
        words[mode == 0 ? where : wordsIndex][mode == 1 ? where : wordsIndex] = word.charAt(wordCount);
    }
  }

  // IMPIRMIR MATRIZ
  public static void printMatrix(char[][] words) {
    // CALCULAR ESPACIO
    int space = words.length < 16 ? (16 - words.length) * 2 : 1;
    System.out.print("\n\n");

    // RECORRER FILAS
    for (int row = 0; row < words.length; row++) {
      // IMPRIMIR FILAS
      System.out.print((repeatString(" ", space) + "-") + repeatString("----", words.length) + "\n");

      // RECORRER COLUMNAS
      for (int col = 0; col < words.length; col++)
        // IMPTIMIR COLUMNAS
        System.out.print((col == 0 ? (repeatString(" ", space) + "| ") : "") + words[row][col] + " | ");

      // SALTO DE LINEA
      System.out.print("\n");
    }

    // MOSTRAR LINEA DE BORDE INFERIOR
    System.out.print((repeatString(" ", space) + "-") + repeatString("----", words.length) + "\n");
  }

  // METODO BURBUJA PARA ORDENAR
  public static StrandInt orderByPoints(int[][] status, String[] names, int length) {
    // DECLARAR NUEVAS LISTAS ORDENADAS
    int bubbledPoints[][] = status;
    String[] orderNames = names;

    // RECORRER ESTADOS
    for (int linear = 0; linear < bubbledPoints.length; linear++) {
      // RECORRER ESTADOS INVERSAMENTE
      for (int inverse = 0; inverse < bubbledPoints.length - linear - 1; inverse++) {
        // SI EL PUNTO DEL ESTADO ACTUAL ES MENOR AL SIGUIENTE
        if (bubbledPoints[inverse][0] < bubbledPoints[inverse + 1][0]) {
          // HACER UN SWIPE DE POSICIONES EN ESTADOS Y NOMBRES
          // GUARDAR VARIABLE TEMPORAL SIGUIENTE
          int[] temporalPoint = bubbledPoints[inverse + 1];
          String temporalName = orderNames[inverse + 1];

          // ASIGNAR LA SIGUIENTE A LA ANTERIOR
          bubbledPoints[inverse + 1] = bubbledPoints[inverse];
          orderNames[inverse + 1] = orderNames[inverse];

          // ASIGNAR ACTUAL A TEMPORAL
          bubbledPoints[inverse] = temporalPoint;
          orderNames[inverse] = temporalName;
        }
      }
    }

    // RETORNAR UN NUEVO TIPO DE DATO INT[][] STRING[]
    return new StrandInt(orderNames, bubbledPoints);
  }
}