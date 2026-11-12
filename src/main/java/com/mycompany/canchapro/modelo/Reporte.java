
package com.mycompany.canchapro.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.table.DefaultTableModel;

public class Reporte {
    public static Object[] reservaFecha(String fechaInicio, String fechaFin) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Cliente");
        modelo.addColumn("Cancha");
        modelo.addColumn("Fecha");
        modelo.addColumn("Horario");
        modelo.addColumn("Monto");
        modelo.addColumn("Estado");
        int totalReserva = 0;

        try {
            FileReader fr = new FileReader("datos/reservas.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            linea = br.readLine();
            while(linea != null) {
                    String[] datos = linea.split(",");
                if(datos.length >= 10) {
                    String fechaReserva = datos[4].trim();

                if(fechaReserva.compareTo(fechaInicio) >= 0 && fechaReserva.compareTo(fechaFin) <= 0) {
                    double monto = Double.parseDouble(datos[7]);
                    modelo.addRow(new Object[]{
                     datos[0],
                     datos[2],
                     datos[3],
                     fechaReserva.trim(),
                     datos[5].trim() + " - " + datos[6],
                     "Q" + String.format("%.2f", monto),
                     datos[9]
                        });
                        totalReserva++;
                        }
                    }
                linea = br.readLine();
                }
            br.close();
        }catch (Exception e) {
            System.out.println("Error en reservaPorFecha: " + e.getMessage());
        }

        String resumen = "Total de reservas encontradas: " + totalReserva;
        return new Object[]{modelo, resumen};
    }
    
    public static Object[] ingresosPeriodo(String fechaInicio, String fechaFin) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Fecha");
        modelo.addColumn("Cliente");
        modelo.addColumn("Monto");
        double totalAcumulado = 0.0;

        try{
            FileReader fr = new FileReader("datos/reservas.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            linea = br.readLine();
            while(linea != null) {
                    String[] datos = linea.split(",");
                    if(datos.length >= 10) {
                        String fechaReserva = datos[4].trim();
                        String estado = datos[9].trim();

                        if(fechaReserva.compareTo(fechaInicio) >= 0 && fechaReserva.compareTo(fechaFin) <= 0 && estado.equalsIgnoreCase("CONFIRMADA")) {
                            double monto = Double.parseDouble(datos[7]);
                            totalAcumulado += monto;

                            modelo.addRow(new Object[]{
                                datos[0],
                                fechaReserva.trim(),
                                datos[2],
                                "Q" + String.format("%.2f", monto)
                            });
                        }
                    }
                    linea = br.readLine();
                }
            br.close();
        }catch (Exception e) {
            System.out.println("Error en ingresosPeriodo: " + e.getMessage());
        }

        String resumen = "Total de ingresos en el periodo: Q" + String.format("%.2f", totalAcumulado);
        return new Object[]{modelo, resumen};
    }

    public static Object[] canchaMasUtilizada() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Cancha");
        modelo.addColumn("Total Reservas");

        String masUtilizada = "Ninguna";
        int masReservas = -1;

        try{
            FileReader fr1 = new FileReader("datos/canchas.txt");
            BufferedReader br1 = new BufferedReader(fr1);
            int totalCanchas = 0;
            while (br1.readLine() != null) totalCanchas++;
            br1.close(); fr1.close();

            if(totalCanchas == 0) return new Object[]{modelo, "No hay canchas registradas."};

            String[] listaCanchas = new String[totalCanchas];
            int[] reservas = new int[totalCanchas];

            FileReader fr2 = new FileReader("datos/canchas.txt");
            BufferedReader br2 = new BufferedReader(fr2);
            String linea;
            int pos = 0;
            
            linea = br2.readLine();
            while(linea != null) {
                    String[] datos = linea.split(",");
                    if(datos.length >= 2) {
                        listaCanchas[pos] = datos[1].trim(); 
                        reservas[pos] = 0;
                        pos++;
                    }
                    linea = br2.readLine();
                }
            br2.close(); fr2.close();

            FileReader fr3 = new FileReader("datos/reservas.txt");
            BufferedReader br3 = new BufferedReader(fr3);
            while((linea = br3.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if(datos.length >= 4) {
                        String canchaReservada = datos[3].trim();
                        for(int i = 0; i < pos; i++) {
                            if(listaCanchas[i] != null && listaCanchas[i].equalsIgnoreCase(canchaReservada)) {
                                reservas[i]++;
                                break;
                            }
                        }
                    }
                }
            br3.close(); 

            for(int i = 0; i < pos; i++) {
                modelo.addRow(new Object[]{listaCanchas[i], reservas[i]});
                if(reservas[i] > masReservas) {
                    masReservas = reservas[i];
                    masUtilizada = listaCanchas[i];
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        String resumen = "La cancha más utilizada es: " + masUtilizada + " con " + (masReservas < 0 ? 0 : masReservas) + " reservas";
        return new Object[]{modelo, resumen};
    }
    
    public static Object[] masReservas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Cliente");
        modelo.addColumn("Total Reservas");

        String[] clientes = new String[200];
        int[] contador = new int[200];
        int cantClientes = 0;

        try{
            FileReader fr = new FileReader("datos/reservas.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            linea = br.readLine();
            while(linea!= null) {
                    String[] datos = linea.split(",");
                    if(datos.length >= 10) {
                        String nombre = datos[2];
                        String estado = datos[9];

                        if(!estado.equalsIgnoreCase("CANCELADA")) {
                            boolean Existe = false;
                            for(int i = 0; i < cantClientes; i++) {
                                if(clientes[i].equalsIgnoreCase(nombre)) {
                                    contador[i]++;
                                    Existe = true;
                                    break;
                                }
                            }
                            if(!Existe && cantClientes < clientes.length) {
                                clientes[cantClientes] = nombre;
                                contador[cantClientes] = 1;
                                cantClientes++;
                            }
                        }
                    }
                    linea = br.readLine();
                }
            br.close(); 
        }catch (Exception e) {
            System.out.println("Error en masReservas: " + e.getMessage());
        }

        int mayor = -1;
        String clienteMasReservas = "Ninguno";

        for(int i = 0; i < cantClientes; i++) {
            modelo.addRow(new Object[]{clientes[i], contador[i]});
            if(contador[i] > mayor) {
                mayor = contador[i];
                clienteMasReservas = clientes[i];
            }
        }
        int total = mayor;
        if(mayor<0){
            total =0;
        }
        String resumen = "El cliente con más reservas: " + clienteMasReservas + " con " +  total  + " reservas";
        return new Object[]{modelo, resumen};
    }
    
    public static Object[] pagosPendientes() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Reserva");
        modelo.addColumn("Cliente");
        modelo.addColumn("Fecha");
        modelo.addColumn("Monto Total");
        modelo.addColumn("Saldo Pendiente");
        double totalPendiente = 0.0;

        try{
            FileReader fr = new FileReader("datos/reservas.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            linea = br.readLine();
            while(linea != null) {
                 String[] datos = linea.split(",");
                    if(datos.length >= 10) {
                        String estado = datos[9].trim();
                        if(estado.equalsIgnoreCase("PENDIENTE")) {
                            double montoTotal = Double.parseDouble(datos[7]);
                            double saldoPen = Double.parseDouble(datos[8]);
                            totalPendiente += saldoPen;

                            modelo.addRow(new Object[]{
                                datos[0],
                                datos[2],
                                datos[4].trim(),
                                "Q" + String.format("%.2f", montoTotal),
                                "Q" + String.format("%.2f", saldoPen)
                            });
                        }
                    }
                    linea = br.readLine();
                }
            br.close(); 
        }catch (Exception e) {
            System.out.println("Error en pagosPendientes: " + e.getMessage());
        }

        String resumen = "Total de saldo por cobrar: Q" + String.format("%.2f", totalPendiente);
        return new Object[]{modelo, resumen};
    }
    
    public static Object[] reservasCanceladas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Cliente");
        modelo.addColumn("Cancha");
        modelo.addColumn("Fecha");
        modelo.addColumn("Horario");
        modelo.addColumn("Monto");
        modelo.addColumn("Estado");
        int totalCanceladas = 0;

        try{
            FileReader fr = new FileReader("datos/reservas.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            linea = br.readLine();
            while(linea != null) {
                    String[] datos = linea.split(",");
                    if(datos.length >= 10) {
                        String estado = datos[9].trim();
                        if(estado.equalsIgnoreCase("CANCELADA")) {
                            double monto = Double.parseDouble(datos[7].trim());
                            modelo.addRow(new Object[]{
                                datos[0],
                                datos[2],
                                datos[3].trim(),
                                datos[4],
                                datos[5] + " - " + datos[6],
                                "Q" + String.format("%.2f", monto),
                                estado
                            });
                            totalCanceladas++;
                        }
                    }
                    linea = br.readLine();
                }
            br.close();
        }catch(Exception e) {
            System.out.println("Error en reservasCanceladas: " + e.getMessage());
        }

        String resumen = "Total de reservas canceladas: " + totalCanceladas;
        return new Object[]{modelo, resumen};
    }

    public static Object[] horariosDemanda() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Horario");
        modelo.addColumn("Total Reservas");
        modelo.addColumn("Demanda");

        String[] horarios = new String[100];
        int[] conteo = new int[100];
        int cantHorarios = 0;

        try{
            FileReader fr = new FileReader("datos/reservas.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            linea = br.readLine();
            while (linea != null) {
                    String[] datos = linea.split(",");
                    if(datos.length >= 10) {
                        String estado = datos[9].trim();
                        if(!estado.equalsIgnoreCase("CANCELADA")) {
                            String horario = datos[5].trim() + " - " + datos[6].trim();
                            boolean coincidencia = false;

                            for(int i = 0; i < cantHorarios; i++) {
                                if (horarios[i].equalsIgnoreCase(horario)) {
                                    conteo[i]++;
                                    coincidencia = true;
                                    break;
                                }
                            }
                            if(!coincidencia && cantHorarios < horarios.length) {
                                horarios[cantHorarios] = horario;
                                conteo[cantHorarios] = 1;
                                cantHorarios++;
                            }
                        }
                    }
                    linea = br.readLine();
                }
            br.close(); fr.close();
        }catch(Exception e) {
            System.out.println("Error en horariosMayorDemanda: " + e.getMessage());
        }

        for (int i = 0; i < cantHorarios - 1; i++) {
            for (int j = 0; j < cantHorarios - 1 - i; j++) {
                if (conteo[j] < conteo[j + 1]) {
                    int auxCant = conteo[j];
                    conteo[j] = conteo[j + 1];
                    conteo[j + 1] = auxCant;

                    String auxHorario = horarios[j];
                    horarios[j] = horarios[j + 1];
                    horarios[j + 1] = auxHorario;
                }
            }
        }

        String horarioMax = "Ninguno";
        int maxDemanda = 0;

        for (int i = 0; i < cantHorarios; i++) {
            String demanda;
            if (i==0){
                demanda = "MAXIMA";
            }else{
                demanda = "REGULAR";
            }
            modelo.addRow(new Object[]{horarios[i], conteo[i], demanda});

            if (i == 0) {
                horarioMax = horarios[i];
                maxDemanda = conteo[i];
            }
        }
        String resumen = "El horario con mayor demanda es: " + horarioMax + " (" + maxDemanda + " reservas)";
        return new Object[]{modelo, resumen};
    }
}
