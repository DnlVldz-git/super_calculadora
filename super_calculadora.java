
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author macbook
 */
public class super_calculadora {
    
     public static final Integer EOF = -1;          

    
    public static void main(String[] args){
        
    String direccion = new String();
    Scanner leer = new Scanner(System.in);    
    
    lista_doble inicio = new lista_doble();
    lista_doble inicio2 = new lista_doble();
    lista_doble inicio3 = new lista_doble();
    lista_doble inicio4 = new lista_doble();            
    lista_doble inicio5 = new lista_doble();
    lista listita = new lista();
    
    
    int fila_grande;
    
    System.out.println("Introduzca la dirección del archivo");          //SE INTRODUCE LA DIRECCIÓN DEL ARCHIVO
    direccion = leer.nextLine();
    
    
    try{
        File file = new File(direccion);       
        FileReader reader = new FileReader(file);
        
        int ascii = 0;
        int numero = 0;
        
        while((ascii = reader.read()) != EOF){                      //SE LEE LO QUE HAY EN EL DOCUMENTO Y SE INTRODUCE EN UNA LISTA
            if(ascii == '\n') {
                inicio = listita.insertar_nodo(ascii);
                continue;
            }
            numero = Character.getNumericValue(ascii);              //PASAMOS LOS CARACTERES DE ASCII A ENTERO
            inicio = listita.insertar_nodo(numero);
        }
        
        reader.close();    
        
        }catch(FileNotFoundException e){
            System.out.println("Hubo un error");
        } catch (IOException ex) {
            Logger.getLogger(lista.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    inicio2 = listita.separar(inicio);                          //SE SEPARA LA LISTA EN LOS DOS, LA LISTA 1  Y 2
    inicio = listita.separar2(inicio);

    
    
    System.out.print('\n');
    fila_grande = listita.comparar(inicio, inicio2);    
    if(fila_grande==1){
        System.out.print("1");
    }else if (fila_grande == 2){
        System.out.print("2");
    } else System.out.println("Las listas son iguales");
    
    switch(fila_grande){                                //CON ESTOS SWITCH SE HACEN LAS OPERACIONES, DEPENDIENDO DEL TAMAÑO DE LAS LISTAS
        case 1:
            inicio3 = listita.suma(inicio, inicio2);
            inicio3=listita.invertir(inicio3);
            inicio4=listita.resta(inicio, inicio2);
            inicio4=listita.invertir(inicio4);
            inicio5 = listita.multiplicacion(inicio, inicio2);
            inicio5=listita.invertir(inicio5);
            
            
            break;
        case 2:
            inicio3 = listita.suma(inicio2, inicio);
            inicio3=listita.invertir(inicio3);
            inicio4=listita.resta(inicio2, inicio);
            inicio4=listita.invertir(inicio4);
            inicio5 = listita.multiplicacion(inicio2, inicio);
            inicio5=listita.invertir(inicio5);
            
            break;
    }
    
    
    
    
    //inicio3 = listita.invertir(inicio3);
    
    


    listita.recorrer(inicio3);
    listita.recorrer(inicio4);
    listita.recorrer(inicio5);
    
    
    try{
        File file_1 = new File("output.txt");       
        FileWriter writer = new FileWriter(file_1);
        PrintWriter pwriter = new PrintWriter(writer);
        
        pwriter.print(fila_grande);
        pwriter.print("\n");
        lista_doble aux = inicio3;
        while(aux!=null){
            pwriter.print(aux.dato);
            aux=aux.siguiente;            
        }
        pwriter.print("\n");
        lista_doble aux2 = inicio4;
        while(aux2!=null){
            pwriter.print(aux2.dato);
            aux2=aux2.siguiente;            
        }
        pwriter.print("\n");
        lista_doble aux3 = inicio5;
        while(aux3!=null){
            pwriter.print(aux3.dato);
            aux3=aux3.siguiente;            
        }
        
        pwriter.close();     
        
        }catch(FileNotFoundException e){
            System.out.println("Hubo un error");
        } catch (IOException ex) {
            Logger.getLogger(lista.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
class lista_doble {
    int dato;
    lista_doble previo;
    lista_doble siguiente;    
}
class lista {
    lista_doble inicio = null;
    
    
    public lista_doble insertar_nodo(int dato){
        lista_doble nuevo = new lista_doble();        
        nuevo.dato=dato;
        nuevo.previo=null;
        nuevo.siguiente=null;
        lista_doble aux = inicio;
        
        if(inicio == null){
            inicio = nuevo;
        } else{
            while(aux.siguiente!=null){
                aux = aux.siguiente;
            }
            nuevo.previo=aux;
            aux.siguiente = nuevo;
        }
        return inicio;
    }
    
    
    public lista_doble separar(lista_doble inicio){
        lista_doble aux = inicio, ant = null;
            inicio.previo=null;
        while(aux != null){
            if(aux.dato == 10){
                aux=aux.siguiente;
                aux.previo=null;
                break;
            }
            ant = aux;
            aux=aux.siguiente;            
        }
        
        inicio = aux;
        
        
        return inicio;
    }
    public int comparar(lista_doble inicio, lista_doble inicio2){
        lista_doble aux1 = inicio, aux2 = inicio2;
        int len1=0, len2=0, suma1 = 0, suma2 = 0;
        while(aux1!=null){
            len1++;
            aux1= aux1.siguiente;
        }
        while(aux2!=null){
            len2++;
            aux2= aux2.siguiente;
        }
        if(len1<len2){
            return 2;
        }else if(len1>len2) return 1;
        if(len1==len2){
            
            aux1 = inicio;            
            aux2 = inicio2;
            while(aux1.siguiente!=null&&aux1.dato!=aux2.dato){
                aux1= aux1.siguiente;
                aux2 =aux2.siguiente;
            }
            
            return (aux1.dato>aux2.dato)?1:2;
        }        
        return 3;
    }
    
    public int acarreo(lista_doble nuevo){
        int acarreo =0;
        if(nuevo.dato>=10&&nuevo.dato<=19){
                nuevo.dato=nuevo.dato-10;
                acarreo=1;
            }else if(nuevo.dato>=20&&nuevo.dato<=29){
                nuevo.dato = nuevo.dato-20;
                acarreo=2;
            }else if(nuevo.dato>=30&&nuevo.dato<=39){
                nuevo.dato = nuevo.dato-30;
                acarreo=3;
            }else if(nuevo.dato>=40&&nuevo.dato<=49){
                nuevo.dato = nuevo.dato-40;
                acarreo=4;
            }else if(nuevo.dato>=50&&nuevo.dato<=59){
                nuevo.dato = nuevo.dato-50;
                acarreo=5;
            }else if(nuevo.dato>=60&&nuevo.dato<=69){
                nuevo.dato = nuevo.dato-60;
                acarreo=6;
            }else if(nuevo.dato>=70&&nuevo.dato<=79){
                nuevo.dato = nuevo.dato-70;
                acarreo=7;
            }else if(nuevo.dato>=80&&nuevo.dato<=89){
                nuevo.dato = nuevo.dato-80;
                acarreo=8;
            }else if(nuevo.dato>=90&&nuevo.dato<=99){
                nuevo.dato = nuevo.dato-90;
                acarreo=9;
            }else if(nuevo.dato>=100&&nuevo.dato<=109){
                nuevo.dato = nuevo.dato-100;
                acarreo=10;
            }else if(nuevo.dato>=110&&nuevo.dato<=119){
                nuevo.dato = nuevo.dato-110;
                acarreo=11;
            }else if(nuevo.dato>=120&&nuevo.dato<=129){
                nuevo.dato = nuevo.dato-120;
                acarreo=12;
            }                      
        return acarreo;
    }
    
    public lista_doble multiplicacion(lista_doble inicio, lista_doble inicio2){
        if(inicio == null){
            return inicio;
        } else if(inicio2 == null){
            return inicio2;
        }
        lista_doble  aux = inicio, aux2=inicio2, ant=null, ant2=null, aux3=null;
        
        lista_doble inicio_n[];
        inicio_n = new lista_doble[300000];
        int i=0, acarreo=0, bandera=0;
        boolean bandera_1 = false;
            
        while(aux!=null){  
            ant=aux;
            aux = aux.siguiente;
        }
        while(aux2!=null){     
            ant2=aux2;
            aux2 = aux2.siguiente;
        }
        aux=ant;
        aux2=ant2;
        while(aux2!=null){      
            
            aux=ant;
            bandera=0;
            bandera_1=false;
            while(aux!=null){
                
                
                
                bandera++;
                
                if(bandera==1){
                    lista_doble nuevo = new lista_doble(); 
                    lista_doble nuevo2 = new lista_doble(); 
                    if(bandera_1 == true){
                        nuevo.dato = aux.dato*aux2.dato+acarreo;
                        bandera_1=false;
                    }else{
                        nuevo.dato = aux.dato*aux2.dato;
                    }
                    
                    if(nuevo.dato>=10){
                        
                        acarreo = acarreo(nuevo);
                        
                        if(aux.previo==null){
                            inicio_n[i]=nuevo;  
                            aux3=inicio_n[i];
                            nuevo2.dato =acarreo;
                            aux3.previo =nuevo2;
                            nuevo2.siguiente = aux3;                            
                            aux=aux.previo;
                            
                            break;                                                        
                        }
                        bandera_1=true;                        
                    }
                    inicio_n[i]=nuevo;  
                    aux3=inicio_n[i];
                    aux = aux.previo;
                    continue;

                }
                if(aux.previo==null){
                    lista_doble nuevo2 = new lista_doble();
                    lista_doble nuevo = new lista_doble(); 
                    if(bandera_1 == true){
                        nuevo.dato = aux.dato*aux2.dato+acarreo;
                        bandera_1=false;
                    }else{
                        nuevo.dato = aux.dato*aux2.dato;
                    }
                    
                    if(nuevo.dato>=10){
                        acarreo = acarreo(nuevo);
                        nuevo2.dato=acarreo;

                        nuevo.siguiente = aux3;
                        aux3.previo = nuevo;                        
                        nuevo2.siguiente = nuevo;
                        nuevo.previo=nuevo2;
                        nuevo2.previo =null;
                        aux=aux.previo;
                        break;
                    }
                    nuevo.siguiente = aux3;
                    nuevo.previo = null;
                    aux3.previo = nuevo;
                    aux3=nuevo;

                    break;
                    
                }
                lista_doble nuevo = new lista_doble();
                
                if(bandera_1 == true){
                        nuevo.dato = aux.dato*aux2.dato+acarreo;
                        bandera_1=false;
                }else{
                        nuevo.dato = aux.dato*aux2.dato;
                }   
                
                aux3.previo = nuevo;
                nuevo.siguiente = aux3;
                aux3=nuevo;
                if(nuevo.dato>=10){
                    acarreo = acarreo(nuevo);
                    bandera_1=true;
                }
                               
                aux=aux.previo;
            }
            i++;
            aux2=aux2.previo;
   
        }
        
        for(int j=0;j<i;j++){
            for(int k=0;k<j;k++){
                lista_doble nuevo= new lista_doble();
                nuevo.dato = 0;
                nuevo.previo = inicio_n[j];
                inicio_n[j].siguiente=nuevo;
                inicio_n[j]=nuevo;
                inicio_n[j].siguiente=null;
            }
        }
        
        lista_doble suma =new lista_doble();
        suma.dato =0;      
        for(int j=0; j<i;j++){
            suma=suma(invertir(inicio_n[j]), invertir(suma));
            }
        return suma;
    }
    public lista_doble division(lista_doble inicio, lista_doble inicio2){
        return inicio;
    }
    
    public lista_doble resta(lista_doble inicio, lista_doble inicio2){
        if(inicio == null){
            return inicio;
        } else if(inicio2 == null){
            return inicio2;
        }
        lista_doble aux =inicio, aux2= inicio2, inicio3=null, aux3=null, aux_1=null;
        int bandera=0, resta=0, bandera_1=0, bandera_2=0;
        lista_doble inicio_1 =new lista_doble();
        lista_doble inicio_2 =new lista_doble();
        while(aux!=null){ 
            bandera_1++;
            if(bandera_1==1){
                lista_doble nuevo = new lista_doble();
                nuevo.dato = aux.dato;
                inicio_1 =nuevo;
                aux_1=inicio_1;

                aux = aux.siguiente;
                
                continue;
            }
            if(aux.siguiente ==null){
                lista_doble nuevo =new lista_doble();
                nuevo.dato=aux.dato;
                nuevo.previo=aux_1;
                aux_1.siguiente = nuevo;
                nuevo.siguiente = null;
                break;
            }
            lista_doble nuevo =new lista_doble();
            nuevo.dato=aux.dato;
            nuevo.previo = aux_1;
            aux_1.siguiente = nuevo;
            aux_1=nuevo;
            aux = aux.siguiente;
        }        
        
        aux=inicio_1;
               
        while(aux.siguiente!=null){               
            aux = aux.siguiente;
            
        }        
        while(aux2.siguiente!=null){               
            aux2 = aux2.siguiente;            
        }
        while(aux!=null&&aux2!=null){
            lista_doble acarreador = aux;
            bandera++;
            if(aux.dato<aux2.dato){
                    do{
                        acarreador=acarreador.previo;

                    }while(acarreador.dato==0&&acarreador != null);
                    do{
                        acarreador.dato=acarreador.dato-1;
                        acarreador=acarreador.siguiente;
                        acarreador.dato=acarreador.dato +10;
                    }while(acarreador!=aux);
            }
           aux.dato=aux.dato-aux2.dato;  
           if(bandera == 1) inicio3=aux;
           aux = aux.previo;
           aux2 = aux2.previo;                                 
        }        
        return inicio3;
    }
    
    
    
    
    
    public lista_doble invertir(lista_doble inicio){
        lista_doble prev, aux = inicio;
        
        while(aux.previo!=null){
            aux = aux.previo;
        }
        inicio = aux;
        
        /*while(aux!=null){
            prev=aux.siguiente;
            aux.siguiente = aux.previo;
            aux.previo=prev;
            
            aux = aux.siguiente;
        }*/
    
    
        return inicio;
    }

       
    public lista_doble suma(lista_doble inicio, lista_doble inicio2){
        if(inicio == null){
            return inicio;
        } else if(inicio2 == null){
            return inicio2;
        }
        int suma = 0, acarreo = 1, bandera = 0, bandera_3=0;
        boolean bandera_2 =false;
        lista_doble inicio3 = new lista_doble();
        lista_doble aux3 = inicio3;
        lista_doble aux = inicio, aux2 = inicio2;    
        aux.previo=null;
        while(aux.siguiente!=null){            
            aux = aux.siguiente;
        }
        aux2.previo=null;
        while(aux2.siguiente!=null){            
            aux2 = aux2.siguiente;
        }
        
        lista_doble piv = aux2;
        while(aux!=null&&aux2!=null&&aux2.dato!=10&&aux.dato !=10){
            
            if(aux.previo == null&&aux2.previo==null&&bandera==0){                
                lista_doble nuevo = new lista_doble();
                nuevo.dato=aux.dato+aux.dato;
                nuevo.previo = null;
                nuevo.siguiente = null;                
                if(nuevo.dato>=10){
                    lista_doble nuevo2 = new lista_doble();
                    nuevo.dato = nuevo.dato -10;
                    nuevo2.dato = 1;
                    nuevo2.siguiente = nuevo;
                    nuevo.previo = nuevo2;
                    nuevo.previo = null;                 
                }
                return inicio;
            }
            
            
            if(bandera_2){
                suma = aux.dato+aux2.dato+1;
                bandera_2 = false;
            }else{
                suma = aux.dato+aux2.dato;                
            }
            if(suma>=10){
                suma = suma -10;
                bandera_2 = true;
                if(aux.previo==null){
                    bandera_3=1;
                }
            }
            
            bandera++;                                       
            aux=aux.previo;
            aux2 = aux2.previo;
            
            if(bandera==1){

                lista_doble nuevo = new lista_doble();
                nuevo.dato=suma;
                inicio3 = nuevo;                 
                aux3 = inicio3;
                
               
                continue;
                
            }
            if(aux == null){
                if(bandera_3 == 1){
                    lista_doble nuevo = new lista_doble();
                    lista_doble nuevo2 = new lista_doble();
                    nuevo.dato=suma;
                    aux3.previo = nuevo;
                    nuevo.siguiente = aux3;
                    nuevo.previo = null;
                    nuevo2.dato = 1;  
                    nuevo2.siguiente = nuevo;
                    nuevo.previo=nuevo2;
                    aux3=nuevo2;
                    aux3.previo = null;
                    
                    return inicio3;
                }
                lista_doble nuevo = new lista_doble();
                nuevo.dato=suma;
                aux3.previo = nuevo;
                nuevo.siguiente = aux3;
                aux3=nuevo;
                nuevo.previo = null;
                break;  
            }
            lista_doble nuevo = new lista_doble();
            aux3.previo=nuevo;
            nuevo.dato=suma;
            nuevo.siguiente = aux3;  
            aux3=nuevo;
            
        }
        
        if(aux==null&&bandera_2==true){   
            lista_doble nuevo_head =null;
            lista_doble aux4=aux3; 
            int bandera_4 = 0;
            
            while(aux2!=null){                               
                bandera_4++;
                if(bandera_4==1){
                    aux4.dato = aux2.dato;
                    aux4.previo= null;                    
                    aux2=aux2.previo;
                    nuevo_head=aux4;                                        
                    continue;
                } 
                lista_doble nuevo = new lista_doble();                
                nuevo.dato=aux2.dato;
                nuevo.siguiente=aux4;
                aux4.previo=nuevo;
                
                
                aux4=aux4.previo;
                aux2=aux2.previo;
            }
            aux4=nuevo_head;                       
            aux4.dato=aux4.dato+1;            
            if(aux4.dato==10){                
                while(aux4.dato==10){  
                    if(aux4.previo ==null) break;
                    aux4.dato=0;
                    
                    aux4=aux4.previo;
                    aux4.dato=aux4.dato +1;
                }
                
                
                if(aux4.dato==10){
                    lista_doble nuevo = new lista_doble();
                    aux4.dato = 0;
                    aux4.previo = nuevo;
                    nuevo.dato=1;
                    nuevo.siguiente=aux4;
                    nuevo.previo = null;
                }
                
            }
            return inicio3;
        }
        if(aux2==null&&bandera_2==true){     
                     
                lista_doble nuevo_head =null;
                lista_doble aux4=aux3; 
                int bandera_4 = 0;
            while(aux!=null){ 
                bandera_4++;
                if(bandera_4==1){
                    lista_doble nuevo = new lista_doble();
                    nuevo.dato = aux.dato;
                    nuevo.previo= null;   
                    nuevo.siguiente = aux4;
                    aux4.previo =nuevo;
                    aux4=nuevo;
                    aux=aux.previo;
                    nuevo_head=nuevo;
                    continue;
                }               
                lista_doble nuevo = new lista_doble();                
                nuevo.dato=aux.dato;
                nuevo.siguiente=aux4;
                aux4.previo=nuevo;
                
                
                aux4=nuevo;
                aux=aux.previo;
            }            
                       
            aux4=nuevo_head;                       
            aux4.dato=aux4.dato+1;
            if(aux4.dato==10){     
                
                while(aux4.dato==10){  
                    if(aux4.previo ==null) break;
                    aux4.dato=0;
                    
                    aux4=aux4.previo;
                    
                    aux4.dato=aux4.dato +1;
                }                              
                
                if(aux4.dato==10){                    
                    lista_doble nuevo = new lista_doble();
                    aux4.dato = 0;
                    aux4.previo = nuevo;
                    nuevo.dato=1;
                    nuevo.siguiente=aux4;
                    nuevo.previo = null;
                }                
            }
            return inicio3;   
        }
        if(aux==null){
            lista_doble nuevo_head =null;
            int bandera_4 = 0;
            lista_doble aux4=aux3; 
            while(aux2!=null){                               
                bandera_4++;
                lista_doble nuevo = new lista_doble();
                
                nuevo.dato=aux2.dato;
                nuevo.siguiente=aux4;
                aux4.previo=nuevo;
                aux4=aux4.previo;
                aux2=aux2.previo;
            }                                            
        }
        if(aux2==null){
            lista_doble nuevo_head =null;
            int bandera_4 = 0;
            lista_doble aux4=aux3;  
            while(aux!=null){                              
                bandera_4++;
                lista_doble nuevo = new lista_doble();
                if(bandera_4 ==1){
                    nuevo_head=nuevo;
                }
                nuevo.dato=aux.dato;
                nuevo.siguiente=aux4;
                aux4.previo=nuevo;
                aux4=aux4.previo;
                aux=aux.previo;
            }            
        }
        
    return inicio3;
    }   
    
    
    public void recorrer_a(lista_doble inicio){
        lista_doble aux = inicio;
        
        while(aux.previo!=null){
            aux = aux.previo;
        }
        while(aux!=null){
            
            System.out.print(aux.dato);
            aux = aux.siguiente;
        }
        
        
        
    }
     
    public lista_doble separar2(lista_doble inicio){
        lista_doble aux = inicio;
       inicio.previo=null;
        while(aux != null){
            if(aux.siguiente.dato == 10){
                aux.siguiente = null;
                break;
            }
           
            aux=aux.siguiente;            
        }
       
        
        return inicio;
    }
    
        public lista_doble recorrer(lista_doble inicio){
     
            if(inicio == null){
                return inicio;
            }
            
            lista_doble aux = inicio;            
            System.out.print("\n");
            
            while (aux != null){             
                System.out.print(aux.dato );                              
                aux = aux.siguiente;                
            }
            
            return inicio;
        }                
}


    
    
    


