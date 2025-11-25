package Logica;

import java.io.Serializable;
import java.util.*;

public class GrafoRutas implements Serializable{
    private Map<Parada, ArrayList<Parada>> listaParadas;
    private Map<String, Parada> traductor;
    private String idRuta;

    public GrafoRutas(String idRuta){
        this.listaParadas = new HashMap<>();
        this.idRuta = idRuta;
        this.traductor = new HashMap<>();
    }

    public String getId(){
        return this.idRuta;
    }

    public void agregarParada(Parada par){
        if(listaParadas.containsKey(par)){
            return;
        }
        traductor.put(par.getId(), par);
        listaParadas.put(par, new ArrayList<>());
    }

    public void conectar(String idParada1, String idParada2){
        Parada parada1 = traductor.get(idParada1);
        Parada parada2 = traductor.get(idParada2);
        if(listaParadas.containsKey(parada1) && listaParadas.containsKey(parada2)){
            listaParadas.get(parada1).add(parada2);
        }
        else{
            System.out.println("No existen");
        }
    }

    public List<Parada> buscarRuta(String idInicio, String idFin){
        Parada inicio = traductor.get(idInicio);
        Parada fin = traductor.get(idFin);

        if(listaParadas.containsKey(inicio) && listaParadas.containsKey(fin)){
            Set<Parada> visitados = new HashSet<>();
            Queue<Parada> cola = new LinkedList<>();
            Map<Parada, Parada> padres = new HashMap<>();

            visitados.add(inicio);
            cola.add(inicio);
            padres.put(inicio, null);

            while(!cola.isEmpty()){
                Parada actual = cola.poll();

                if (actual.equals(fin)){
                    return reconstruirRuta(padres, fin);
                }

                for(Parada vecino: listaParadas.getOrDefault(actual, null)){
                    if(!visitados.contains(vecino)){
                        visitados.add(vecino);
                        padres.put(vecino, actual);
                        cola.add(vecino);
                    }
                }
            }
        }
        return null;
    }

    private List<Parada> reconstruirRuta(Map<Parada, Parada> padres, Parada destino){
        List<Parada> camino = new ArrayList<Parada>();
        Parada actual = destino;
        while (actual != null) {
            camino.add(actual);
            actual = padres.get(actual);
        }
        Collections.reverse(camino);
        return camino;
    }

    public int numeroParadas(String inicio, String fin){
        if(listaParadas.isEmpty()){
            return -1;
        }

        List<Parada> ruta = buscarRuta(inicio, fin);
        if(ruta == null){
            return -1;
        }
        return ruta.size()-1;
    }
}
