import java.util.Scanner;

public class FirstTry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ARRAY UNIDIMENSIONAL-> ALMACEN DE DATOS
        String[][] usuarios = new String[10][2];												 // [0]=GUARDAR NOMBRE USUARIO, [1]=GUARDAR CONTRASEÑA
        int totalUsuarios = 0;

        String[][] salas = new String[10][4];   												 // [0]=GUARDAR CODIGO DE SALA, [1]=GUARDAR ANFITION, [2]=GUARDAR ROL USUARIO, [3]=GUARDAR GASTO
        int totalSalas = 0;

        boolean salirApp = false;

        // MENÚ PRINCIPAL
        do {
            System.out.println("SPENDWISE - MENÚ PRINCIPAL");
            System.out.println("-------------------------------------");
            System.out.println("1. REGISTRASE");
            System.out.println("2. INICIAR SESIÓN");
            System.out.println("3. SALIR");
            System.out.println("-------------------------------------");
            System.out.print("SELECCIONE UNA OPCION: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            String userActivo = "";		                										 // VARIABLE PARA SABER QUIEN HA INICIADO SESIÓN

            // OPCIÓN 1: REGISTRO
            if (opcion == 1) {
                System.out.print("NOMBRE DE USUARIO: ");
                	usuarios[totalUsuarios][0] = scanner.nextLine();
                System.out.print("CONTRASEÑA: ");
                	usuarios[totalUsuarios][1] = scanner.nextLine();
                
                userActivo = usuarios[totalUsuarios][0]; 										// SE LOGUEA DIRECTO POR LA VARIABLE USERACTIVO
                totalUsuarios++;
                System.out.println("->REGISTRO CORRECTO, BIENVENID@ " + userActivo + "!");

            // OPCIÓN 2: INICIAR SESIÓN
            } else if (opcion == 2) {
                System.out.print("NOMBRE DE USUARIO: ");
                	String usuario = scanner.nextLine();
                System.out.print("CONTRASEÑA: ");
                	String contraseña = scanner.nextLine();

                boolean loginOk = false; 														//INTERRUPTOR APAGADO HASTA QUE SE ENCUENTRE EL USUARIO Y CONTRASEÑA CORRECTOS
                for (int i = 0; i < totalUsuarios; i++)	{ 										//VA MIRANDO TODA LA LISTA DE USUARIOS
                    if (usuarios[i][0].equals(usuario) && usuarios[i][1].equals(contraseña)) {  //USUARIO Y CONTRASEÑA TEMPORAL COINCIDE CON EL DE LA LISTA
                        loginOk = true; 														//SI EL USUARIO Y CONTRASEÑA COINCIDEN, SE ENCIENDE EL INTERRUPTOR
                        break;
                    }
                }

	                if (loginOk) {
	                    userActivo = usuario;
	                    System.out.println("-> ¡USUARIO ENCONTRADO. BIENBEND@ " + usuario + "!");
	                } else {
	                    System.out.println("-> ERROR: USUARIO O CONTRASEÑA INCORRECTOS.");
	                }

            } else if (opcion == 3) {
            	System.out.println("¡CERRANDO PROGRAMA, GRACIAS POR USAR SPENDWISE!");
                break; 
            }

            // SI HAY UN USUARIO LOGUEADO, ENTRA AL MENÚ DE SALAS
            if (!userActivo.equals("")) { 														//COMPROBACION DE USUARIO Y CONTRASEÑA CORRECTOS
                boolean enMenuSala = true; 														//INTERRUMTOR APAGADO, EL MENU SE REPITE HASTA QUE SALES

                do {
                    System.out.println("\n--- MENÚ DE SALAS (" + userActivo + ") ---");
                    System.out.println("1. Crear sala");
                    System.out.println("2. Unirse a sala");
                    System.out.println("3. Añadir gasto y dividir (Solo Anfitrión)");
                    System.out.println("4. Ver gastos de la sala");
                    System.out.println("5. Cerrar sesión");
                    System.out.print("Seleccione opción: ");
                    int opcSala = scanner.nextInt();
                    scanner.nextLine();

                    // 1. CREAR SALA
                    if (opcSala == 1) {
	                        System.out.print("INTRODUCE EL CODIGO DE LA NUEVA SALA: ");
	                        String codigo = scanner.nextLine();
	
	                        boolean yaExiste = false; 														//INTERRUPTOR APAGADO, SE ENCIENDE SI HAY UNA SALA CON ESE CODIGO
	                        for (int i = 0; i < totalSalas; i++) {
	                            if (salas[i][0].equals(codigo)) { yaExiste = true; break; }
	                        }

                        if (yaExiste) {
                            System.out.println("-> ERROR, YA EXISTE UNA SALA CON ESE CÓDIGO.");
                        } else {
	                            salas[totalSalas][0] = codigo;        								   //CODIGO DE LA SALA
	                            salas[totalSalas][1] = userActivo; 										 //NOMBRE DE USUARIO QUE CREA LA SALA (ANFITRIÓN)
	                            salas[totalSalas][2] = "ADMIN";  										 //ROL DEL ADMIN ASIGNADO	
	                            salas[totalSalas][3] = "0"; 											 //CONTADOR DE GASTOS INICIALIZADO A 0
	                            totalSalas++;
                            System.out.println("¡SALA CREADA CON EXITO!");
                        }

                    // 2. UNIRSE A SALA
                    } else if (opcSala == 2) {
                        System.out.print("INTRODUCE EL CODIGO DE LA SALA A LA QUE QUIERES UNIRTE: ");
                        String codigoBuscado = scanner.nextLine();

                        boolean encontrada = false;														//INTERRUPTOR APAGADO, SE ENCIENDE SI HAY UNA SALA CON ESE CODIGO
                        for (int i = 0; i < totalSalas; i++) {
                            if (salas[i][0].equals(codigoBuscado)) { encontrada = true; break; }
                        }

                        if (encontrada) {
                            System.out.println("-> ¡Te has unido correctamente a la sala!");
                        } else {
                            System.out.println("-> Error: La sala no existe.");
                        }

                    // 3. AÑADIR GASTO (SOLO ANFITRIÓN)
                    } else if (opcSala == 3) {
                        System.out.print("Introduce código de la sala: ");
                        String codGasto = scanner.nextLine();

                        int index = -1;																 // CREA LA VARIABLE INDEX PARA SABER EN QUE POSICION DEL ARRAY SE ENCUENTRA LA SALA
                        for (int i = 0; i < totalSalas; i++) {											 // EMPEIZA EL BUCLE PARA BUSCAR LA SALA EN EL ARRAY
                            if (salas[i][0].equals(codGasto)) { index = i; break; } 					// SI ENCUENTRA LA SALA, GUARDA SU POSICION EN INDEX Y SALE DEL BUCLE
                        }

                        if (index != -1) {
                            // Comprobar si soy el anfitrión
                            if (salas[index][1].equals(userActivo)) {									 //SI YA ENCONTRO UNA SALA CON ESE CODIGO CONFIRMA SI ES EL USUARIO QUE LA CREO (ANFITRION)
                                System.out.print("Gasto total (€): ");
                                double total = scanner.nextDouble();
                                System.out.print("Número de personas: ");
                                int personas = scanner.nextInt();
                                scanner.nextLine();

                                if (personas > 0) {
                                    double resultado = total / personas;
                                    salas[index][3] = String.valueOf(resultado); 											// GUARDAR EL RESULTADO DEL GASTO EN EL ARRAY DE LA SALA
                                    System.out.println("-> Gasto repartido. A cada uno le toca: " + resultado + " €");
                                } else {
                                    System.out.println("-> Error: Debe haber al menos 1 persona.");
                                }
                            } else {
                                System.out.println("-> Error: Solo el anfitrión (" + salas[index][1] + ") puede añadir gastos.");
                            }
                        } else {
                            System.out.println("-> Error: La sala no existe.");
                        }

                    // 4. VER GASTOS
                    } else if (opcSala == 4) {
                        System.out.print("Introduce código de la sala a consultar: ");
                        String codConsulta = scanner.nextLine();

                        int index = -1;																		// CREA LA VARIABLE INDEX PARA SABER EN QUE POSICION DEL ARRAY SE ENCUENTRA LA SALA
                        for (int i = 0; i < totalSalas; i++) {												// EMPEIZA EL BUCLE PARA BUSCAR LA SALA EN EL ARRAY
                            if (salas[i][0].equalsIgnoreCase(codConsulta)) { index = i; break; }			// SI ENCUENTRA LA SALA, GUARDA SU POSICION EN INDEX Y SALE DEL BUCLE
                        }

                        if (index != -1) {																				 //SI EL INDEX NO ES -1, SIGNIFICA QUE ENCONTRO LA SALA Y PUEDE MOSTRAR LOS GASTOS
                            String gasto = salas[index][3];                                                              //GUARDAR EL GASTO DE LA SALA EN LA VARIABLE GASTO EXTRAIDO DE LA 3º COLUMNA DEL ARRAY DE SALAS
                            System.out.println("----------------------------------------");
                            System.out.println("Sala: " + salas[index][0] + " | Anfitrión: " + salas[index][1]);         //NOMBRE DE LA SALA Y NOMBRE DEL ANFITRION
                            if (gasto.equals("0")) {
                                System.out.println("Estado: Aún no hay gastos registrados.");
                            } else {
                                System.out.println("A pagar por persona: " + gasto + " €");
                            }
                            System.out.println("----------------------------------------");
                        } else {
                            System.out.println("-> Error: La sala no existe.");
                        }

                    // 5. CERRAR SESIÓN
                    } else if (opcSala == 5) {
                        enMenuSala = false;                                       	       //LA VARIABLE ENMENUSALA SE PONE A FALSE PARA SALIR DEL BUCLE Y VOLVER AL MENU PRINCIPAL
                        System.out.println("-> Sesión cerrada.");
                    }

                } while (enMenuSala);                        						      //SE MANTIENE EN TRUE REPITIENDO EL MENU DE SALAS HASTA QUE EL USUARIO CIERRE SESION Y SE PONGA EN FALSE
            }

        } while (!salirApp);															//SE MANTIENE EN TRUE REPITIENDO EL MENU DE PRINCIPAL HASTA QUE EL USUARIO SELECCIONE LA OPCION 3 PARA SALIR Y SE PONGA EN FALSE

        scanner.close();
    }
}