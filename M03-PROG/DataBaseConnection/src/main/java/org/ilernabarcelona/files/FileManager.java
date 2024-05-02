package org.ilernabarcelona.files;

import org.ilernabarcelona.vialacteadesabores.*;




import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Map;

public class FileManager {
    public ArrayList<Usuario> cargaUsuariosCSV(String rutaDelArchivoCSV) {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaDelArchivoCSV))) {
            String line;
            // Ignora la primera línea (encabezados)
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                int id_usuario = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String apellido1 = datos[2];
                String apellido2 = datos[3];
                Date fecha_nacimiento = Date.valueOf(datos[4]);
                String email = datos[5];
                Date fecha_registro = Date.valueOf(datos[6]);
                String telefono = datos[7];
                String contrasena = datos[8];
                // Crea un nuevo objeto Usuario y se añade al array
                Usuario usuario = new Usuario(id_usuario, nombre, apellido1, apellido2, fecha_nacimiento,
                        email, fecha_registro, telefono,contrasena);
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public ArrayList<Chef> cargaChefsCSV(String rutaDelArchivoCSV) {
        ArrayList<Chef> chefs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaDelArchivoCSV))) {
            String line;
            // Ignora la primera línea (encabezados)
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                int id_usuario = Integer.parseInt(datos[0]);
                double salario = Double.parseDouble(datos[1]);
                Date fecha_contratacion = Date.valueOf(datos[2]);
                boolean disponible = Boolean.parseBoolean(datos[3]);
                // Crea un nuevo objeto Usuario y se añade al array
                Chef chef = new Chef(id_usuario, salario, fecha_contratacion, disponible);
                chefs.add(chef);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return chefs;
    }

    public ArrayList<Camarero> cargaCamarerosCSV(String rutaDelArchivoCSV) {
        ArrayList<Camarero> camareros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaDelArchivoCSV))) {
            String line;
            // Ignora la primera línea (encabezados)
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                int id_usuario = Integer.parseInt(datos[0]);
                double salario = Double.parseDouble(datos[1]);
                Date fecha_contratacion = Date.valueOf(datos[2]);
                boolean disponible = Boolean.parseBoolean(datos[3]);
                // Crea un nuevo objeto Usuario y se añade al array
                Camarero camarero = new Camarero(id_usuario, salario, fecha_contratacion, disponible);
                camareros.add(camarero);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return camareros;
    }

    public ArrayList<Mesa> cargaMesaCSV(String rutaDelArchivoCSV) {
        ArrayList<Mesa> mesas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaDelArchivoCSV))) {
            String line;
            // Ignora la primera línea (encabezados)
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                int num_mesa = Integer.parseInt(datos[0]);
                int id_camarero = Integer.parseInt(datos[1]);
                Date fecha_registro = Date.valueOf(datos[2]);
                boolean disponibilidad = Boolean.parseBoolean(datos[3]);
                // Crea un nuevo objeto Usuario y se añade al array
                Mesa mesa = new Mesa(num_mesa, id_camarero, fecha_registro, disponibilidad);
                mesas.add(mesa);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mesas;
    }

    public ArrayList<Cliente> cargaClientesCSV(String fileInput) {
        ArrayList<Cliente> clientesList = new ArrayList<>();
        Cliente clientes;

        try (BufferedReader br = new BufferedReader(new FileReader(fileInput))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 3) {
                    clientes = new Cliente(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Date.valueOf(fields[2]));
                    clientesList.add(clientes);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("🔎 KO -> ARCHIVO NO ENCONTRADO: " + fileInput);
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return clientesList;
    }

    public ArrayList<Cliente_Mesa> cargaClientes_MesasCSV(String fileInput) {
        ArrayList<Cliente_Mesa> clientes_mesasList = new ArrayList<>();
        Cliente_Mesa clientesMesa;

        try (BufferedReader br = new BufferedReader(new FileReader(fileInput))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 3) {
                    clientesMesa = new Cliente_Mesa(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Date.valueOf(fields[2]));
                    clientes_mesasList.add(clientesMesa);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("🔎 KO -> ARCHIVO NO ENCONTRADO: " + fileInput);
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return clientes_mesasList;
    }

    public ArrayList<Comanda> cargaComandasCSV(String fileInput) {
        ArrayList<Comanda> comandas = new ArrayList<>();
        Comanda comanda;
        try (BufferedReader br = new BufferedReader(new FileReader(fileInput))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 4) {
                    comanda = new Comanda(Integer.parseInt(fields[0]), Date.valueOf(fields[1]), Boolean.parseBoolean(fields[2]), Integer.parseInt(fields[3]));
                    comandas.add(comanda);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("🔎 KO -> ARCHIVO NO ENCONTRADO: " + fileInput);
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return comandas;
    }

    public ArrayList<Menu> cargaMenuCSV(String fileInput) {
        ArrayList<Menu> menus = new ArrayList<>();
        Menu menu;
        try (BufferedReader br = new BufferedReader(new FileReader(fileInput))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 4) {
                    menu = new Menu(Integer.parseInt(fields[0]), fields[1], fields[2], Double.parseDouble(fields[3]));
                    menus.add(menu);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("🔎 KO -> ARCHIVO NO ENCONTRADO: " + fileInput);
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return menus;
    }

    public ArrayList<Menu_Comanda> cargaMenu_ComandasCSV(String fileInput) {
        ArrayList<Menu_Comanda> menus_comandas = new ArrayList<>();
        Menu_Comanda menu_comanda;
        try (BufferedReader br = new BufferedReader(new FileReader(fileInput))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 4) {
                    menu_comanda = new Menu_Comanda(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2].split("/"));
                    menus_comandas.add(menu_comanda);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("🔎 KO -> ARCHIVO NO ENCONTRADO: " + fileInput);
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return menus_comandas;
    }

    public ArrayList<Plato> cargaPlatoCSV(String fileInput) {
        ArrayList<Plato> platos = new ArrayList<>();
        Plato plato;
        try (BufferedReader br = new BufferedReader(new FileReader(fileInput))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 4) {
                    plato = new Plato(Integer.parseInt(fields[0]), fields[1], fields[2], Integer.parseInt(fields[3]));
                    platos.add(plato);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("🔎 KO -> ARCHIVO NO ENCONTRADO: " + fileInput);
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return platos;
    }

    public ArrayList<Menu_Plato> cargaMenu_Plato(String fileInput) {
        ArrayList<Menu_Plato> menus_platos = new ArrayList<>();
        Menu_Plato menuPlato;
        try (BufferedReader br = new BufferedReader(new FileReader(fileInput))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 2) {
                    menuPlato = new Menu_Plato(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]));
                    menus_platos.add(menuPlato);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("🔎 KO -> ARCHIVO NO ENCONTRADO: " + fileInput);
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return menus_platos;
    }

    public ArrayList<Producto> cargaProductosCSV(String fileInput) {
        ArrayList<Producto> productos = new ArrayList<>();
        Producto producto;
        try (BufferedReader br = new BufferedReader(new FileReader(fileInput))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 4) {
                    producto = new Producto(Integer.parseInt(fields[0]), fields[1], Integer.parseInt(fields[2]), fields[3]);
                    productos.add(producto);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("🔎 KO -> ARCHIVO NO ENCONTRADO: " + fileInput);
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return productos;

    }

    public ArrayList<Plato_Producto> cargaPlato_Producto(String fileInput) {
        ArrayList<Plato_Producto> platos_productos = new ArrayList<>();
        Plato_Producto plato_producto;
        try (BufferedReader br = new BufferedReader(new FileReader(fileInput))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 2) {
                    plato_producto = new Plato_Producto(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]));
                    platos_productos.add(plato_producto);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return  platos_productos;
    }

    private static class MyArray implements Array {
        public MyArray(String[] fields) {
            super();
        }

        @Override
        public String getBaseTypeName() throws SQLException {
            return "";
        }

        @Override
        public int getBaseType() throws SQLException {
            return 0;
        }

        @Override
        public Object getArray() throws SQLException {
            return null;
        }

        @Override
        public Object getArray(Map<String, Class<?>> map) throws SQLException {
            return null;
        }

        @Override
        public Object getArray(long index, int count) throws SQLException {
            return null;
        }

        @Override
        public Object getArray(long index, int count, Map<String, Class<?>> map) throws SQLException {
            return null;
        }

        @Override
        public ResultSet getResultSet() throws SQLException {
            return null;
        }

        @Override
        public ResultSet getResultSet(Map<String, Class<?>> map) throws SQLException {
            return null;
        }

        @Override
        public ResultSet getResultSet(long index, int count) throws SQLException {
            return null;
        }

        @Override
        public ResultSet getResultSet(long index, int count, Map<String, Class<?>> map) throws SQLException {
            return null;
        }

        @Override
        public void free() throws SQLException {

        }
    }
}
