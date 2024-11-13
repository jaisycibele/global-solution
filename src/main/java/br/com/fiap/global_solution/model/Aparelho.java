package br.com.fiap.global_solution.model;
import jakarta.persistence.*;
import java.util.List;

    @Entity
    @Table(name = "tb_aparelho")
    public class Aparelho {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idAparelho;

        @Column(name = "nome", nullable = false)
        private String nome;

        @Column(name = "potencia", nullable = false)
        private Long potencia;

        @Column(name = "tipo", nullable = false)
        private String tipo;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "usuario_id", referencedColumnName = "idUsuario", foreignKey = @ForeignKey(name = "FK_APARELHO_USUARIO"))
        private Usuario usuario;

        @OneToMany(mappedBy = "aparelho", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Consumo> consumos;

        public Long getIdAparelho() {
            return idAparelho;
        }

        public void setIdAparelho(Long idAparelho) {
            this.idAparelho = idAparelho;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Long getPotencia() {
            return potencia;
        }

        public void setPotencia(Long potencia) {
            this.potencia = potencia;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public Usuario getUsuario() {
            return usuario;
        }

        public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
        }

        public List<Consumo> getConsumos() {
            return consumos;
        }

        public void setConsumos(List<Consumo> consumos) {
            this.consumos = consumos;
        }
    }
