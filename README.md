# Campanha sócio-torcedor
API REST campanhas de sócio-torcedor
Que Fornece macanismo para cadastro de campanhas para sócio-torcedores.

*Ao cadastrar uma campanha o sistema verifica se existem cmapanhas dentro do mesmo período caso positivo soma um dias na data de termino. O sistema também verifica se há campanha com o mesmo ternimo e caso positivo adiciona mais um dia.

Para completude do sistema é necessário a integração com o serviço de cadastro de usuário, para que o usuario se associe a uma campanha

### GET /campanhas
[image lista](https://drive.google.com/file/d/1dM_VmtGEtnQU_e9sMvVF_7NsiesDlBU1/view?usp=sharing)


### GET /campanha/:id
[image detalhe](https://drive.google.com/file/d/1aUQ__EljETYh3r1nggIq6Cy3vxZ6GMmW/view?usp=sharing)


### POST /campanha
[image create](https://drive.google.com/file/d/1qeJLD1nE4fEjVL9qhLVt2jr0t1Aiv2fQ/view?usp=sharing)


### DELETE /campanha/:id
```
localhost:8080/campanha/{id}

```
