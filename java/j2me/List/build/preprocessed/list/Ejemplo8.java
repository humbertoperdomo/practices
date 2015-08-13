/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package list;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


public class Ejemplo8 extends MIDlet implements CommandListener {
    public static final int CONECTAR= 0;
    public static final int ESTADO= 1;
    public static final int INSERTAR= 2;
    public static final int DESCONECTAR= 3;
    public static final int MENU= 4;

    private Display display;

    private Alert alerta;
    private List menu;
    private TextBox estado;
    private Form form;

    private TextField server;
    private TextField area;
    private ChoiceGroup riesgo; 
    private ChoiceGroup aficiones; 

    private Command salir; 
    private Command continuar; 

    private int estado_actual;

    //Constructor
    public Ejemplo8(  ) {
	estado_actual=MENU;

	//Cogemos el display
	display=Display.getDisplay(this);

        //Creamos los items Form
 	  //un item para preguntar el nombre del servidor
          server= new TextField("Servidor:","",10,TextField.URL);
 	  //un item para mostrar info
	  estado =new TextBox("Estado","Sin Conexion", 400, TextField.ANY);
 	  //un item para pedir un area
	  area =new TextField("Area ID","0", 10, TextField.NUMERIC);
 	  //un item para indicar un riesgo
          riesgo= new ChoiceGroup("Riesgo:",ChoiceGroup.EXCLUSIVE);
	  riesgo.append("Bajo",null);
	  riesgo.append("Normal",null);
	  riesgo.append("Alto",null);

        //Creamos el menu y le anadimos las opciones
        menu = new List ("Ejemplo 8",List.EXCLUSIVE);
        menu.append("Conectar",null); 
        menu.append("Pedir Datos",null); 
        menu.append("Introducir Datos",null); 
        menu.append("Desconectar",null); 

	//creamos un alert
	alerta= new Alert("Error","No hay conexion",null, AlertType.ERROR); 
	alerta.setTimeout(Alert.FOREVER);

	//creamos un form
	form = new Form ("Menu Conectar");

	//Creamos los comandos a usar
	salir=new Command("Salir",Command.EXIT, 3);
	continuar=new Command("Continuar",Command.OK, 1);

        //anadimos los comandos al List y activamos el oyente
	menu.addCommand(continuar);
	menu.addCommand(salir);
        menu.setCommandListener(this);

	//Anadimos al form sus comandos
	form.addCommand(continuar);
	form.addCommand(salir);
        form.setCommandListener(this);

        //anadimos al textBox sus comandos
	estado.addCommand(salir);
        estado.setCommandListener(this);
    }
 
    //Metodo que se llama cuando pasamos de Pausado a Activo
    protected void startApp(  ) {
        display.setCurrent(menu);
    }
 
    //Metodo que se llama cuando pasamos de Activo a Pausado
    protected void pauseApp(  ) {
    }
 
    //Metodo que se llama cuando se destruye el midlet
    protected void destroyApp(boolean incondicional) {
    }

    //Metodo para el tratamiento de datos de teclado 
    public void commandAction(Command c, Displayable d) {

	if (estado_actual == MENU ) {
	    //si estamos en el menu, nos salimos o nos vamos
	    //a la opcion escogida
	    if (c ==salir) {
		destroyApp(true);
		notifyDestroyed();
	    } else if (c ==continuar) {
		switch ( menu.getSelectedIndex() ){
		 case CONECTAR: conectar(); break;
		 case ESTADO: listar();break;
		 case INSERTAR: insertar();break;
		 case DESCONECTAR: desconectar();break;
		}
	    } else System.out.println("Otro comando pulsado");

	} else {
	    //si no estamos en el menu, nos vamos al menus o procesamos
	    //los datos
	    if (c == salir) {
	
		muestraMenu();
	    } else if (c ==continuar) {
		switch ( estado_actual ){
		 case CONECTAR: listar();break;
		 case ESTADO: muestraMenu();break;
		 case INSERTAR: listar();break;
		 case DESCONECTAR: muestraMenu();break;
		}
	    } else System.out.println("Otro comando pulsado");
	}
    }

    public void conectar(){
	estado_actual=CONECTAR; 
	//cambiamos el titulo del form
	form.setTitle("Menu Conectar");
	//Borramos todos los items del form
	while (form.size()>0)
	    form.delete(0);
	form.append(server);

	display.setCurrent(form);
    }

    public void insertar(){
	estado_actual=INSERTAR;
	//cambiamos el titulo del form
	form.setTitle("Menu Insertar");
	//Borramos todos los items del form
	while (form.size()>0)
	    form.delete(0);
	form.append(area);
	form.append(riesgo);

	display.setCurrent(form);
    }

    public void muestraAlerta(String titulo, String texto){
	alerta.setTitle(titulo);
	alerta.setString(texto);
	//mostramos un alert
	display.setCurrent(alerta);
    }

    public void desconectar(){
	estado_actual=DESCONECTAR;
	//mostramos un alert
	muestraAlerta("Desconectar","No se esta conectado a ningun servidor.");
	estado_actual=MENU;
    }

    public void listar(){
	estado_actual=ESTADO;
	//mostramos el textbox
	display.setCurrent(estado);
    }

    public void muestraMenu(){
	//mostramos el List
	estado_actual=MENU;
	display.setCurrent(menu);
    }
}