package prueba.sdes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class PruebaSDES {
    
    static String [][] S0 = {{"01","00","11","10"},{"11","10","01","00"},{"00","10","01","11"}, {"11","01","11","10"}};
    static String [][] S1 = {{"00","01","10","11"},{"10","00","01","11"},{"11","00","01","00"}, {"10","01","00","11"}};
    private static int[] K1 = new int [8];
    private static int[] K2 = new int [8];
    
    
    public static void main(String[] args) throws IOException {
        System.out.println("Dame tu numero");
        
        Scanner leer = new Scanner(System.in);
        
        String numero = "0000010110";
                //leer.nextLine();
        char[] cadenaChar = new char[10];
        cadenaChar = numero.toCharArray();
        int[] cadenaInt = new int[10];
        
        for (int i = 0; i < 10; i++)
        {
            cadenaInt[i] = cadenaChar[i];
           
            //48 porque char 0 es int 48
            //49 porque char 1 es int 49
            if(cadenaInt[i] == 48)
                cadenaInt[i] = 0;
            else
                cadenaInt[i] = 1;
        }
        
        // Se generan la llaves con la cadena ingresada por el usuario
        GenerarLlaves(cadenaInt);
        
        String k1 = "";
        String k2 = "";
        
        // Se crea K1 y K2
        for(int i = 0; i < 8; i++)
        {
            k1 += String.valueOf(K1[i]);
            k2 += String.valueOf(K2[i]);
        }
        
        System.out.println(k1);
        System.out.println(k2);
        
        System.out.println("");
        
        // Lee un archivo de texto
        File Archivo = new File("C:\\Users\\Admin\\Desktop\\Integrantes.txt");
        char[] TextoaCifrar = Lectura(Archivo);
        String TextoparaEscribir = "";
        
        // Cifra caracter por caracter
        for(int i = 0; i < TextoaCifrar.length; i++)
        {
            char letraCifrada = CifrarSDES(TextoaCifrar[i]);
            TextoparaEscribir = TextoparaEscribir + String.valueOf(letraCifrada);
        }
        
        // Imprime lo concatenado cifrado
        System.out.println(TextoparaEscribir);
        System.out.println("");
        
        // Se convierte el texto cifrado en un arreglo de char
        char[] TextoaDescifrar = TextoparaEscribir.toCharArray();
        TextoparaEscribir = "";
        
        // Se descifra el texto cifrado
        for(int i = 0; i< TextoaDescifrar.length; i++)
        {
            char letraDesifrada = DescifrarSDES(TextoaDescifrar[i]);
            TextoparaEscribir = TextoparaEscribir + String.valueOf(letraDesifrada);
        }
        
        // Se Imprime el texto descifrado
        
        String salida = TextoparaEscribir.replace('±', '\n');
        System.out.println(salida);
    }
   
    
    public static int[] RellenarCeros(char Letra)
    {
        int numero = Letra;
        String Aux = Integer.toBinaryString(numero);
        char[] Arrayderelleno = Aux.toCharArray();
        int [] ArrayRellenado = new int[8];
        
        if(Arrayderelleno.length < 8)
        {
            
            int Cantidad = 8 - Aux.length();
            String ceros = "";
            
            for(int i = 0; i< Cantidad; i++)
            {
                ceros = ceros + "0";
            }
            
            Aux = ceros+Aux;
            Arrayderelleno = Aux.toCharArray();
            
            for(int i = 0; i < Arrayderelleno.length; i++)
            {
                ArrayRellenado[i] = Arrayderelleno[i];
                
                if(ArrayRellenado[i] == 48)
                    ArrayRellenado[i] = 0;
                else 
                    ArrayRellenado[i] = 1;
            }     
            
        }
        else
        {
            for(int i = 0; i< Arrayderelleno.length; i++)
            {
                ArrayRellenado[i] = Arrayderelleno[i];
                
                if(ArrayRellenado[i] == 48)
                    ArrayRellenado[i] = 0;
                else 
                    ArrayRellenado[i] = 1;
            }
        }
        
        
        return ArrayRellenado;
    }
    
    public static char[] Lectura(File Archivo) throws FileNotFoundException, IOException
    {
        if(Archivo.exists()==true)
        {
            String Texto = "";
            FileReader LecturaArchivo;
            LecturaArchivo = new FileReader(Archivo);
            BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);
            String Linea="";
            String SiguienteLinea="";
            Linea = LeerArchivo.readLine();
            
            while(Linea != null)
            {
                SiguienteLinea = LeerArchivo.readLine();
                Texto += Linea;

                if (SiguienteLinea != null)
                    Texto += "±";

                Linea = SiguienteLinea;
            }
            LecturaArchivo.close();
            LeerArchivo.close();

            return Texto.toCharArray();
        }
        else
        {
         return "-1".toCharArray();
        }
    }
    
    public static void Proceso() throws IOException
    {
        //Se Selecciona un Archivo, con el fin de probar por el momento sera este /Desktop/Prueba.txt
        File Archivo = new File("C:\\Users\\josue\\Desktop");
        HashMap<Integer,Character> AlmacendeLetras = new HashMap<Integer,Character>();
        
        //Se deben Generar las llaves para luego entrar a SDES
        //GenerarLlaves(NUMERO QUE INGRESA EL USUARIO);
        
        char[] Texto = Lectura(Archivo);
        for(char item: Texto)
        {
            int Auxiliar = Integer.parseInt(String.valueOf(item));
            if(AlmacendeLetras.containsKey(Auxiliar))
            {
                //El Mensaje Codificado puede ser un StringBuilder y solo ir concatenando el resultado de cada iteracion del ciclo, y de ultimo convertirlo a byte todo.
                //Se debe de buscar la letra, sino se encuentra entonces se debe hacer el proceso de codificado
            }else
            {
                //Hay que crear una funcion que complete los bytes por si la letra que se selecciona no cumple el byte completo (Los 8 Bits)
                //Se codifica la letra y se agrega al Diccionario
                //int CaracteraCodificar = ;
             
            }
        }
        
    }
    
    
    public static void GenerarLlaves(int [] Entrada)
    {
        int[] temp = new int[10];
        temp = P10(Entrada);
        
        int[] Parte1 = new int[5];
        int[] Parte2 = new int[5];
        
        for (int i = 0; i < 5; i++)
        {
            Parte1[i] = temp[i];
            Parte2[i] = temp[i+5];
        }
   
        Parte1 = LS1(Parte1);
        Parte2 = LS1(Parte2);
        
        for(int i = 0 ; i < 5; i++)
            temp[i] = Parte1[i];
        
        for(int i = 5; i < 10; i++)
            temp[i] = Parte2[i-5];
        
        K1 = P8(temp);
        
        Parte1 = LS2(Parte1);
        Parte2 = LS2(Parte2);
        
        for(int i = 0 ; i < 5; i++)
            temp[i] = Parte1[i];
        
        for(int i = 5; i < 10; i++)
            temp[i] = Parte2[i-5];
        
        K2 = P8(temp);
    }
    
    
    public static char CifrarSDES(char Letra)
    {
        int[] temp = new int[8];
        temp = RellenarCeros(Letra);
        
        temp = PInicial(temp);
        
        int[] Externo1 = new int[4];
        int[] Externo2 = new int[4];
        
        for (int i = 0; i < 4; i++)
        {
            Externo1[i] = temp[i];
            Externo2[i] = temp[i+4];
        }
        
        int[] TempInterno = new int[8];
        
        TempInterno = EP(Externo2);
        TempInterno = XOR(TempInterno, K1);
        
        int[] SBox = new int[4];
        
        SBox = SBoxes(TempInterno);
        SBox = P4(SBox);
        
        Externo1 = XOR(Externo1, SBox);
        
        // SE DAN VUELTA EXTERNO1 Y EXTERNO2 (PRESENTACION)
        
        temp = new int[8];
        TempInterno = new int[8];
        
        TempInterno = EP(Externo1);
        TempInterno = XOR(TempInterno, K2);
        
        SBox = new int[4];
        
        SBox = SBoxes(TempInterno);
        SBox = P4(SBox);
        
        Externo2 = XOR(Externo2, SBox);
        
        for (int i = 0; i < 4; i++)
            temp[i] = Externo2[i];
        
        for (int i = 4; i < 8; i++)
            temp[i] = Externo1[i-4];
        
        int[] cifrado = new int[8];
        cifrado = IPInverso(temp);
        
        String texto = "";
        
        for (int i = 0; i < 8; i++)
            texto += cifrado[i];
        
        int numero = Integer.parseInt(texto,2);
        char letraCifrado = (char) numero;
                
        return letraCifrado;
    }
    
    public static char DescifrarSDES(char Letra)
    {
        int[] temp = new int[8];
        temp = RellenarCeros(Letra);
        
        temp = PInicial(temp);
        
        int[] Externo1 = new int[4];
        int[] Externo2 = new int[4];
        
        for (int i = 0; i < 4; i++)
        {
            Externo1[i] = temp[i];
            Externo2[i] = temp[i+4];
        }
        
        int[] TempInterno = new int[8];
        
        TempInterno = EP(Externo2);
        TempInterno = XOR(TempInterno, K2);
        
        int[] SBox = new int[4];
        
        SBox = SBoxes(TempInterno);
        SBox = P4(SBox);
        
        Externo1 = XOR(Externo1, SBox);
        
        // SE DAN VUELTA EXTERNO1 Y EXTERNO2 (PRESENTACION)
        
        temp = new int[8];
        TempInterno = new int[8];
        
        TempInterno = EP(Externo1);
        TempInterno = XOR(TempInterno, K1);
        
        SBox = new int[4];
        
        SBox = SBoxes(TempInterno);
        SBox = P4(SBox);
        
        Externo2 = XOR(Externo2, SBox);
        
        for (int i = 0; i < 4; i++)
            temp[i] = Externo2[i];
        
        for (int i = 4; i < 8; i++)
            temp[i] = Externo1[i-4];
        
        int[] cifrado = new int[8];
        cifrado = IPInverso(temp);
        
        String texto = "";
        
        for (int i = 0; i < 8; i++)
            texto += cifrado[i];
        
        int numero = Integer.parseInt(texto,2);
        char letraDescifrado = (char) numero;
                
        return letraDescifrado;   
    }
    
    //PROCESO
     public static int[] LS1(int[] Cadena)
    {
        int temp = Cadena[0];
        
        for (int i = 0; i < 4; i++)
            Cadena[i] = Cadena[i+1];
        
        Cadena[4] = temp;
        
        return Cadena;
    }
    
     //PROCESO
    public static int[] LS2(int[] Cadena)
    {
        int temp1 = Cadena[0];
        int temp2 = Cadena[1];
        
        for (int i = 0; i < 3; i++)
            Cadena[i] = Cadena[i+2];
        
        Cadena[3] = temp1;
        Cadena[4] = temp2;
        
        return Cadena;
    }
    
    //LLAVES
    public static int[] IPInverso(int[] Entrada)
    {
        Constantes Datos = new Constantes();
        int [] ArregloAuxiliar = new int[Entrada.length];
                
        
        for(int i = 0; i < 8; i++)
        {
            ArregloAuxiliar[Datos.PermutacionInicial[i]] = Entrada[i];
        }
        
        return ArregloAuxiliar;   
    }
    
    //PROCESO
    private static int[] XOR (int[] Comparador1, int[] Comparador2)
    {
        int[] XOR = new int[Comparador1.length];
        
        for (int i = 0; i < Comparador1.length; i++)
        {
            if(Comparador1[i] == Comparador2[i])
                XOR[i] = 0;
            else
                XOR[i] = 1;
        }
        
        return XOR;
    }
    
    //PROCESO
    public static int[] SBoxes(int [] Arreglo)
    {
        String Cadena;
        int[] Auxiliar = new int[4];
        String [] S0box = new String [2];
        String [] S1box = new String [2];
        
        String Aux = "";
        
        Aux = String.valueOf(Arreglo[0]);
        Aux = Aux + String.valueOf(Arreglo[3]);
        S0box[0] = Aux;
        Aux = "";
        Aux = String.valueOf(Arreglo[1]);
        Aux = Aux + String.valueOf(Arreglo[2]);
        S0box[1] = Aux;
        
        Aux = String.valueOf(Arreglo[4]);
        Aux = Aux + String.valueOf(Arreglo[7]);
        S1box[0] = Aux;
        Aux = "";
        Aux = String.valueOf(Arreglo[5]);
        Aux = Aux + String.valueOf(Arreglo[6]);
        S1box[1] = Aux;
        
       
        int Fila = Integer.valueOf(S0box[0],2);
        int Columna = Integer.valueOf(S0box[1],2);
        
        Cadena = S0[Fila][Columna];
        
        Fila = Integer.valueOf(S1box[0],2);
        Columna = Integer.valueOf(S1box[1],2);
        
        Cadena = Cadena + S1[Fila][Columna];
        
        char[] cadenaSeparada = Cadena.toCharArray();
        for (int i = 0; i < cadenaSeparada.length; i++) 
        {
            Auxiliar[i] = cadenaSeparada[i];
            
            if(Auxiliar[i] == 48)
                    Auxiliar[i] = 0;
                else 
                    Auxiliar[i] = 1;
        }
         
        return Auxiliar;
    }
     
    //PROCESO
    public String BinarioaString(int [] texto)
    {

        String Auxiliar = texto.toString();
        int TextoBinario = Integer.valueOf(Auxiliar,2);
        String Letra = String.valueOf((char) TextoBinario);

        return Letra;
    }
    
    //LLAVE
    public static int[] PInicial(int[] Entrada)
    {
        Constantes Datos = new Constantes();
        int [] ArregloAuxiliar = new int[Entrada.length];
                
        
        for(int i = 0; i< Datos.PermutacionInicial.length; i++)
        {
            ArregloAuxiliar[i] = Entrada[Datos.PermutacionInicial[i]];
        }
        
        return ArregloAuxiliar;
    }
    
    //LLAVE
    public static int[] P10(int[] Entrada)
    {
        Constantes Datos = new Constantes();
        int [] ArregloAuxiliar = new int[Entrada.length];
                
        
        for(int i = 0; i< Datos.P10.length; i++)
        {
            ArregloAuxiliar[i] = Entrada[Datos.P10[i]];
        }
        
        return ArregloAuxiliar;
    }
    
    //LLAVE
    public static int[] P8(int[] Entrada)
    {
       Constantes Datos = new Constantes();
        int [] ArregloAuxiliar = new int[Datos.P8.length];
                
        
        for(int i = 0; i< Datos.P8.length; i++)
        {
            ArregloAuxiliar[i] = Entrada[Datos.P8[i]];
        }
        
        return ArregloAuxiliar; 
    }
    
    //LLAVE
    public static int [] P4(int[] Entrada)
    {
       Constantes Datos = new Constantes();
        int [] ArregloAuxiliar = new int[Entrada.length];
                
        
        for(int i = 0; i< Datos.P4.length; i++)
        {
            ArregloAuxiliar[i] = Entrada[Datos.P4[i]];
        }
        
        return ArregloAuxiliar;     
    }
    
    //LLAVE
    public static int[] EP(int[] Entrada)
    {
       Constantes Datos = new Constantes();
        int [] ArregloAuxiliar = new int[Datos.EP.length];
                
        
        for(int i = 0; i<3; i++)
        {
            ArregloAuxiliar[i] = Entrada[Datos.EP[i]];
        }
        
        for(int i = 3; i<=7; i++)
        {
            ArregloAuxiliar[i] = Entrada[Datos.EP[i]];
        }
        
        return ArregloAuxiliar;      
    }
    
}
