package ps2.lab06;

import jakarta.persistence.*;

@Entity
@Table(name = "professores")
public class Professor {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private String cpf;
    private int matricula;

    @ManyToOne(optional = false)
    private Faculdade faculdade;

    public Professor() {
        super();
    }

    public Professor(String n, String c, int m, Faculdade f) {
        nome = n;
        cpf = c;
        matricula = m;
        faculdade = f;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String n) {
        nome = n;
    }

    public String getNome() {
        return nome;
    }

    public void setCpf(String c) {
        cpf = c;
    }

    public String getCpf() {
        return cpf;
    }

    public void setMatricula(int m) {
        matricula = m;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setFaculdade(Faculdade f) {
        faculdade = f;
    }

    public Faculdade getFaculdade() {
        return faculdade;
    }
}