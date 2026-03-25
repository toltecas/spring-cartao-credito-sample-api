package sample.br.cartaoCredito.model.dto;


import jakarta.validation.constraints.NotBlank;


public class MembroFamiliaDTO {


   @NotBlank(message = "O Nome do Membro da Família deve ser Informado.")
   private String nome;
   @NotBlank(message = "A Relacao Familiar do Membro da Família deve ser Informado.")
   private String relacaoFamiliar;
  
   public String getNome() {
       return nome;
   }


   public void setNome(String nome) {
       this.nome = nome;
   }


   public String getRelacaoFamiliar() {
       return relacaoFamiliar;
   }


   public void setRelacaoFamiliar(String relacaoFamiliar) {
       this.relacaoFamiliar = relacaoFamiliar;
   }
}