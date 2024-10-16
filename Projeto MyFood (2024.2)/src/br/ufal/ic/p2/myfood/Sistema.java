package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Exceptions.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;


public class Sistema {
    private static Gson gson;
    private static final String FILE_PATH = "users.json";
    private Map<Integer, Usuario> usuarios;
    private Map<Integer, Empresa> empresas;
    private Map<Integer, Pedido> pedidos;
    private int nextUserId;
    private int nextEmpresaId;
    private int nextProdutoId;
    private int nextPedidoId;


    public Sistema() {
        usuarios = new HashMap<>();
        empresas = new HashMap<>();
        pedidos =  new HashMap<>();
        nextUserId = 1;
        nextEmpresaId = 1;
        nextProdutoId = 1;
        nextPedidoId = 1;
        configurarGson();
    }

    public void zerarSistema() {
        this.nextUserId = 1;
        this.nextEmpresaId = 1;
        this.usuarios.clear();
        this.empresas.clear();
        this.pedidos.clear();
        salvarUsuarios();
    }
    private void configurarGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(Usuario.class, new UsuarioTypeAdapter())
                    .registerTypeAdapter(Empresa.class, new EmpresaTypeAdapter())
                    .create();
        }
    }
    public class UsuarioTypeAdapter implements JsonDeserializer<Usuario>, JsonSerializer<Usuario> {
        @Override
        public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String tipo = jsonObject.get("tipo").getAsString();

            switch (tipo) {
                case "Cliente":
                    return context.deserialize(json, UsuarioCliente.class);
                case "Dono":
                    return context.deserialize(json, UsuarioDono.class);
                case "Entregador":
                    return context.deserialize(json, UsuarioEntregador.class);
                default:
                    throw new JsonParseException("Unknown element type: " + tipo);
            }
        }
        @Override
        public JsonElement serialize(Usuario src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = context.serialize(src).getAsJsonObject();
            jsonObject.addProperty("tipo", src.getTipo());
            return jsonObject;
        }
    }
    public static Sistema carregarDados(String caminhoArquivoUsuarios) throws IOException {
        Sistema sistema = new Sistema();
        sistema.configurarGson();
        try (Reader reader = new FileReader(caminhoArquivoUsuarios)) {
            Type mapType = new TypeToken<Map<Integer, Usuario>>() {}.getType();
            Map<Integer, Usuario> loadedUsers = gson.fromJson(reader, mapType);
            if (loadedUsers != null) {
                for (Usuario usuario : loadedUsers.values()) {
                    sistema.usuarios.put(usuario.getId(), usuario);
                }
                sistema.nextUserId = loadedUsers.keySet().stream().max(Integer::compare).orElse(0) + 1;
            }
        } catch (FileNotFoundException e) {
        }
        try (Reader reader = new FileReader("empresas.json")) {
            Type mapType = new TypeToken<Map<Integer, Empresa>>() {}.getType();
            Map<Integer, Empresa> loadedEmpresas = gson.fromJson(reader, mapType);
            if (loadedEmpresas != null) {
                for (Empresa empresa : loadedEmpresas.values()) {
                    sistema.empresas.put(empresa.getId(), empresa);
                }
                sistema.nextEmpresaId = loadedEmpresas.keySet().stream().max(Integer::compare).orElse(0) + 1;
            }
        } catch (FileNotFoundException e) {
        }

        //se der problema no 4_1 ou 4_2 verificar isso aq e verificar zerarSistema

        try (Reader reader = new FileReader("pedidos.json")) {
            Type mapType = new TypeToken<Map<Integer, Pedido>>() {}.getType();
            Map<Integer, Pedido> loadedPedidos = gson.fromJson(reader, mapType);
            if (loadedPedidos != null) {
                for (Pedido pedido : loadedPedidos.values()) {
                    sistema.pedidos.put(pedido.getNumero(), pedido);
                }
                sistema.nextPedidoId = loadedPedidos.keySet().stream().max(Integer::compare).orElse(0) + 1;
            }
        } catch (FileNotFoundException e) {
        }
        return sistema;
    }
    public void adicionarUsuario(Usuario user) {
        usuarios.put(user.getId(), user);
        salvarUsuarios();
    }
    public void salvarUsuarios() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //usuario
    public Usuario criarUsuario(String nome, String email, String senha, String endereco) throws Exception {
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (email == null || !email.contains("@")) {
            throw new EmailInvalidoException();
        }
        if (senha == null || senha.isEmpty()) {
            throw new SenhaInvalidaException();
        }
        if (endereco == null || endereco.isEmpty()) {
            throw new EnderecoInvalidoException();
        }
        for (Usuario usuarioExistente : usuarios.values()) {
            if (usuarioExistente.getEmail().equals(email)) {
                throw new EmailJaExisteException();
            }
        }
        Usuario usuarioC = new UsuarioCliente(nextUserId++, nome, email, senha, endereco);
        usuarios.put(usuarioC.getId(), usuarioC);
        salvarUsuarios();
        return usuarioC;
    }
    //dono
    public Usuario criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (email == null || !email.contains("@")) {
            throw new EmailInvalidoException();
        }
        if (senha == null || senha.isEmpty()) {
            throw new SenhaInvalidaException();
        }
        if (endereco == null || endereco.isEmpty()) {
            throw new EnderecoInvalidoException();
        }
        if (cpf == null || cpf.length() != 14) {
            throw new CpfInvalidoException();
        }
        for (Usuario usuario2 : usuarios.values()) {
            if (usuario2.getEmail().equals(email)) {
                throw new EmailJaExisteException();
            }
        }
        Usuario usuarioDono = new UsuarioDono(nextUserId++, nome, email, senha, endereco, cpf);
        usuarios.put(usuarioDono.getId(), usuarioDono);
        salvarUsuarios();
        return usuarioDono;
    }
    //entregador
    public Usuario criarUsuario(String nome, String email, String senha, String endereco, String veiculo, String placa) throws Exception {
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (email == null || !email.contains("@")) {
            throw new EmailInvalidoException();
        }
        if (senha == null || senha.isEmpty()) {
            throw new SenhaInvalidaException();
        }
        if (endereco == null || endereco.isEmpty()) {
            throw new EnderecoInvalidoException();
        }
        // o programa precisa verificar se o usuario é tipo entregador antes de verificar se placa/veiculo é null
        // se não um usuario dono/cliente cai na exceções embaixo
        if(veiculo == null || veiculo.isEmpty()) { throw new VeiculoInvalidoException();}
        if(placa == null || placa.isEmpty()) { throw new PlacaInvalidaException();}
        for (Usuario usuarioExistente : usuarios.values()) {
            if (usuarioExistente.getEmail().equals(email)) {
                throw new EmailJaExisteException();
            }
        }
        UsuarioEntregador usuarioE = new UsuarioEntregador(nextUserId++, nome, email, senha, endereco, veiculo, placa);
        usuarios.put(usuarioE.getId(), usuarioE);
        salvarUsuarios();
        return usuarioE;
    }
    public int login(String email, String senha) throws Exception {
        if (email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
            throw new LoginOuSenhaInvalidosException();
        }
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario.getId();
            }
        }
        throw new LoginOuSenhaInvalidosException();
    }
    public String getAtributoUsuario(int id, String atributo) throws Exception {
        Usuario usuario1 = usuarios.get(id);
        if (usuario1 != null) {
            switch (atributo) {
                case "nome":
                    return usuario1.getNome();
                case "email":
                    return usuario1.getEmail();
                case "senha":
                    return usuario1.getSenha();
                case "endereco":
                    return usuario1.getEndereco();
                case "veiculo":
                    if(usuario1 instanceof UsuarioEntregador) return ((UsuarioEntregador) usuario1).getVeiculo();
                case "placa":
                    if(usuario1 instanceof UsuarioEntregador) return ((UsuarioEntregador) usuario1).getPlaca();
                case "cpf":
                    if (usuario1 instanceof UsuarioDono) {
                        return ((UsuarioDono) usuario1).getCpf();
                    } else {
                        throw new AtributoInvalidoException();
                    }
                default:
                    throw new AtributoInvalidoException();
            }
        } else {
            throw new UsuarioNaoCadastradoException();
        }
    }
    public class EmpresaTypeAdapter implements JsonDeserializer<Empresa> {
        @Override
        public Empresa deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String tipo = jsonObject.get("tipo").getAsString();
            if (tipo.equals("restaurante")) {
                return context.deserialize(json, Restaurante.class);
            } else if (tipo.equals("mercado")) {
                return context.deserialize(json, Mercado.class);
            }
            else if (tipo.equals("farmacia")) {
                return context.deserialize(json, Farmacia.class);
            }
            return context.deserialize(json, Empresa.class);
        }
    }
    //restaurante
    public int criarEmpresa(String tipoEmpresa, int donoId, String nome, String endereco, String tipoCozinha) throws Exception {
        Usuario dono = findUsuarioById(donoId);
        if (!(dono instanceof UsuarioDono)) {
            throw new UsuarioNaoPodeCriarEmpresaException();
        }
        for (Empresa empresa : empresas.values()) {
            if (empresa.getNome().equals(nome)) {
                if (empresa.getDono().getId() != donoId) {
                    throw new EmpresaComMesmoNomeDonoDiferenteException();
                }
                if (empresa.getDono().getId() == donoId && empresa.getEndereco().equals(endereco)) {
                    throw new EmpresaComMesmoNomeEEnderecoException();
                }
            }
        }
        Restaurante novaEmpresa = new Restaurante(nextEmpresaId++, nome, endereco, tipoCozinha, dono);
        empresas.put(novaEmpresa.getId(), novaEmpresa);
        salvarEmpresas();
        return novaEmpresa.getId();
    }
    //farmacia
    public int criarEmpresa(String tipoEmpresa, int donoId, String nome, String endereco, Boolean aberto24Horas, int numeroFuncionarios) throws Exception{
        Usuario dono = findUsuarioById(donoId);
        if (!(dono instanceof UsuarioDono)) {
            throw new UsuarioNaoPodeCriarEmpresaException();
        }
        if (tipoEmpresa == null || tipoEmpresa.isEmpty()) { throw new TipoEmpresaInvalidoException(); }
        if(nome == null || nome.isEmpty()|| nome.equals("")) { throw new NomeInvalidoException(); }
        if(endereco == null || endereco.equals("") || endereco.isEmpty() || endereco.equals(" ")) { throw new EnderecoEmpresaInvalido(); }
        for (Empresa empresa : empresas.values()) {
            if (empresa.getNome().equals(nome)) {
                if (empresa.getDono().getId() != donoId) {
                    throw new EmpresaComMesmoNomeDonoDiferenteException();
                }
                if (empresa.getDono().getId() == donoId && empresa.getEndereco().equals(endereco)) {
                    throw new EmpresaComMesmoNomeEEnderecoException();
                }
            }
        }
        Farmacia novaEmpresa = new Farmacia(nextEmpresaId++, nome, endereco, aberto24Horas, numeroFuncionarios, dono);
        empresas.put(novaEmpresa.getId(), novaEmpresa);
        salvarEmpresas();
        return novaEmpresa.getId();
    }
    private void salvarEmpresas() {
        try (Writer writer = new FileWriter("empresas.json")) {
            gson.toJson(empresas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getAtributoEmpresa(int empresaId, String atributo) throws Exception {
        Empresa empresa = findEmpresaById(empresaId);
        if (empresa == null) {
            throw new EmpresaNaoCadastradaException();
        }
        if (atributo == null || atributo.isEmpty()) {
            throw new AtributoInvalidoException();
        }
        switch (atributo) {
            case "nome":
                return empresa.getNome();
            case "endereco":
                return empresa.getEndereco();
            case "tipo":
                return empresa.getTipo();
            case "tipoCozinha":
                if (empresa instanceof Restaurante) {
                    return ((Restaurante) empresa).getTipoCozinha();
                } else {
                    throw new AtributoInvalidoException();
                }
            case "abre":
                if (empresa instanceof Mercado) {
                    return ((Mercado) empresa).getAbre();
                } else {
                    throw new AtributoInvalidoException();
                }
            case "fecha":
                if (empresa instanceof Mercado) {
                    return ((Mercado) empresa).getFecha();
                } else {
                    throw new AtributoInvalidoException();
                }
            case "tipoMercado":
                if (empresa instanceof Mercado){
                    return ((Mercado) empresa).getTipoMercado();
                } else {
                    throw new AtributoInvalidoException();
                }
            case "aberto24Horas":
                if(empresa instanceof Farmacia) return Boolean.toString(((Farmacia) empresa).getAberto24Horas());
            case "numeroFuncionarios":
                if(empresa instanceof Farmacia) return String.valueOf(((Farmacia) empresa).getNumeroFuncionarios());
            case "dono":
                return empresa.getDono().getNome();
            default:
                throw new AtributoInvalidoException();
        }
    }
    public String getEmpresasDoUsuario(int idDono) throws Exception {
        Usuario dono = findUsuarioById(idDono);
        if (!(dono instanceof UsuarioDono)) {
            throw new UsuarioNaoPodeCriarEmpresaException();
        }
        StringBuilder resultado = new StringBuilder("{[");
        for (Empresa empresa : empresas.values()) {
            if (empresa.getDono().getId() == idDono) {
                resultado.append("[").append(empresa.getNome()).append(", ").append(empresa.getEndereco()).append("], ");
            }
        }
        if (resultado.length() > 2) {
            resultado.setLength(resultado.length() - 2);
        }
        resultado.append("]}");
        return resultado.toString();
    }
    public int getIdEmpresa(int idDono, String nome, int indice) throws Exception {
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        Usuario dono = findUsuarioById(idDono);
        if (!(dono instanceof UsuarioDono)) {
            throw new UsuarioNaoPodeCriarEmpresaException();
        }
        List<Empresa> empresasDoDono = new ArrayList<>();
        for (Empresa empresa : empresas.values()) {
            if (empresa.getDono().getId() == idDono && empresa.getNome().equals(nome)) {
                empresasDoDono.add(empresa);
            }
        }
        if (indice <= -1) {
            throw new IndiceInvalidoException();
        }
        if (empresasDoDono.isEmpty()) {
            throw new NaoExisteEmpresaComEsseNomeException();
        }
        if (indice >= empresasDoDono.size()) {
            throw new IndiceMaiorException();
        }
        return empresasDoDono.get(indice).getId();
    }
    private Usuario findUsuarioById(int id) throws UsuarioNaoCadastradoException {
        Usuario usuario = usuarios.get(id);
        if (usuario == null) {
            throw new UsuarioNaoCadastradoException();
        }
        return usuario;
    }
    private Empresa findEmpresaById(int id) {
        return empresas.get(id);
    }
    public int criarProduto(int empresaId, String nome, float valor, String categoria) throws Exception {
        Empresa empresa = findEmpresaById(empresaId);
        if (empresa == null) {
            throw new EmpresaNaoCadastradaException();
        }
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (valor <= 0) {
            throw new ValorInvalidoException();
        }
        if (categoria == null || categoria.isEmpty()) {
            throw new CategoriaInvalidaException();
        }
        for (Produto produto : empresa.getProdutos()) {
            if (produto.getNome().equals(nome)) {
                throw new ProdutoJaExisteException();
            }
        }
        Produto novoProduto = new Produto(nextProdutoId++, nome, valor, categoria);
        empresa.adicionarProduto(novoProduto);
        salvarEmpresas();
        return novoProduto.getId();
    }
    public void editarProduto(int produtoId, String nome, float valor, String categoria) throws Exception {
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (valor <= 0) {
            throw new ValorInvalidoException();
        }
        if (categoria == null || categoria.isEmpty()) {
            throw new CategoriaInvalidaException();
        }
        boolean produtoEditado = false;
        for (Empresa empresa : empresas.values()) {
            Produto produto = empresa.findProdutoById(produtoId);
            if (produto != null) {
                empresa.editarProduto(produtoId, nome, valor, categoria);
                produtoEditado = true;
                break;
            }
        }
        if (!produtoEditado) {
            throw new ProdutoNaoCadastradoException();
        }
        salvarEmpresas();
    }
    public String getProduto(String nome, int empresaId, String atributo) throws Exception {
        Empresa empresa = findEmpresaById(empresaId);
        if (empresa == null) {
            throw new EmpresaNaoCadastradaException();
        }
        Produto produto = empresa.getProduto(nome);
        if (produto == null) {
            throw new ProdutoNaoEncontradoException();
        }
        switch (atributo) {
            case "nome":
                return produto.getNome();
            case "valor":
                DecimalFormat df = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.US));
                return df.format(produto.getValor());
            case "categoria":
                return produto.getCategoria();
            case "empresa":
                return empresa.getNome();
            default:
                throw new AtributoNaoExisteException();
        }
    }
    public String listarProdutos(int empresaId) throws Exception {
        Empresa empresa = findEmpresaById(empresaId);
        if (empresa == null) {
            throw new EmpresaNaoEncontradaException();
        }
        List<String> produtos = empresa.listarProdutos();
        if (produtos.isEmpty()) {
            return "{[]}";
        }
        StringBuilder resultado = new StringBuilder("{[");
        for (String produto : produtos) {
            resultado.append(produto).append(", ");
        }
        if (resultado.length() > 2) {
            resultado.setLength(resultado.length() - 2);
        }
        resultado.append("]}");
        return resultado.toString();
    }
    public int criarPedido(int clienteId, int empresaId) throws Exception {
        Usuario cliente = findUsuarioById(clienteId);
        if (cliente == null || cliente instanceof UsuarioDono) {
            throw new DonoNaoPodeFazerPedidoException();
        }
        Empresa empresa = findEmpresaById(empresaId);
        if (empresa == null) {
            throw new EmpresaNaoCadastradaException();
        }
        for (Pedido pedido : pedidos.values()) {
            if (pedido.getCliente().getId() == clienteId && pedido.getEmpresa().getId() == empresaId && pedido.getEstado().equals("aberto")) {
                throw new DoisPedidosEmAberto();
            }
        }
        Pedido novoPedido = new Pedido(nextPedidoId++, cliente, empresa);
        pedidos.put(novoPedido.getNumero(), novoPedido);
        salvarPedidos();
        return novoPedido.getNumero();
    }
    public void adicionarProduto(int numeroPedido, int produtoId) throws Exception {
        Pedido pedido = findPedidoById(numeroPedido);
        if (pedido == null) {
            throw new PedidoNaoEmAbertoException();
        }
        if (pedido.getEstado().equals("preparando")) {
            throw new AdicionarPedidoFechado();
        }
        if (!pedido.getEstado().equals("aberto")) {
            throw new PedidoNaoEncontradoException();
        }
        Produto produto = findProdutoById(produtoId);
        if (produto == null || !pedido.getEmpresa().getProdutos().contains(produto)) {
            throw new ProdutoNaoPertenceAEmpresaException();
        }
        pedido.adicionarProduto(produto);
        salvarPedidos();
    }
    public void fecharPedido(int numeroPedido) throws Exception {
        Pedido pedido = findPedidoById(numeroPedido);
        if (pedido == null) {
            throw new PedidoNaoEncontradoException();
        }
        pedido.setEstado("preparando");
        salvarPedidos();
    }
    public void removerProduto(int numeroPedido, String nomeProduto) throws Exception {
        Pedido pedido = findPedidoById(numeroPedido);
        if (pedido == null) {
            throw new PedidoNaoEncontradoException();
        }
        if (pedido.getEstado().equals("preparando")) {
            throw new RemoverPedidoFechado();
        }
        if (nomeProduto == null || nomeProduto.isEmpty()) {
            throw new ProdutoInvalidoException();
        }
        Produto produto = null;
        for (Produto p : pedido.getProdutos()) {
            if (p.getNome().equals(nomeProduto)) {
                produto = p;
                break;
            }
        }
        if (produto == null) {
            throw new ProdutoNaoEncontradoException();
        }
        pedido.getProdutos().remove(produto);
        salvarPedidos();
    }
    private void salvarPedidos() {
        try (Writer writer = new FileWriter("pedidos.json")) {
            gson.toJson(pedidos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Pedido findPedidoById(int pedidoId) {
        return pedidos.get(pedidoId);
    }
    public Produto findProdutoById(int produtoId) {
        for (Empresa empresa : empresas.values()) {
            Produto produto = empresa.findProdutoById(produtoId);
            if (produto != null) {
                return produto;
            }
        }
        return null;
    }
    public String getPedidos(int numeroPedido, String atributo) throws Exception {
        Pedido pedido = findPedidoById(numeroPedido);
        if (pedido == null) {
            throw new PedidoNaoEncontradoException();
        }
        if (atributo == null || atributo.isEmpty()) {
            throw new AtributoInvalidoException();
        }
        switch (atributo) {
            case "cliente":
                return pedido.getCliente().getNome();
            case "empresa":
                return pedido.getEmpresa().getNome();
            case "estado":
                return pedido.getEstado();
            case "produtos":
                StringBuilder produtosStr = new StringBuilder("{[");
                for (Produto produto : pedido.getProdutos()) {
                    produtosStr.append(produto.getNome()).append(", ");
                }
                if (produtosStr.length() > 2) {
                    produtosStr.setLength(produtosStr.length() - 2);
                }
                produtosStr.append("]}");
                return produtosStr.toString();
            case "valor":
                return String.format(Locale.US, "%.2f", pedido.getProdutos().stream().mapToDouble(Produto::getValor).sum());
            default:
                throw new AtributoNaoExisteException();
        }
    }
    public int getNumeroPedido(int clienteId, int empresaId, int indice) throws Exception {
        Usuario cliente = findUsuarioById(clienteId);
        if (cliente == null) {
            throw new UsuarioNaoCadastradoException();
        }
        Empresa empresa = findEmpresaById(empresaId);
        if (empresa == null) {
            throw new EmpresaNaoCadastradaException();
        }
        List<Pedido> pedidosDoCliente = new ArrayList<>();
        for (Pedido pedido : pedidos.values()) {
            if (pedido.getCliente().getId() == clienteId && pedido.getEmpresa().getId() == empresaId) {
                pedidosDoCliente.add(pedido);
            }
        }
        if (indice < 0 || indice >= pedidosDoCliente.size()) {
            throw new IndiceInvalidoException();
        }
        return pedidosDoCliente.get(indice).getNumero();
    }
    public Mercado findMercadoById(int mercadoId) throws MercadoNaoEncontradoException {
        for (Empresa empresa : empresas.values()) {
            if (empresa instanceof Mercado && empresa.getId() == mercadoId) {
                return (Mercado) empresa;
            }
        }
        throw new MercadoNaoEncontradoException();
    }
    //mercado
    public int criarEmpresa(String tipoEmpresa, int donoId, String nome, String endereco, String abre, String fecha, String tipoMercado) throws Exception{
        Usuario dono = findUsuarioById(donoId);
        if (!(dono instanceof UsuarioDono)) {
            throw new UsuarioNaoPodeCriarEmpresaException();
        }
        if (tipoEmpresa == null || tipoEmpresa.isEmpty()) {
            throw new TipoEmpresaInvalidoException();
        }
        if (tipoMercado == null || tipoMercado.isEmpty()) {
            throw new TipoMercadoInvalido();
        }
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (endereco == null || endereco.isEmpty()) {
            throw new EnderecoEmpresaInvalido();
        }
        if (abre == null || fecha == null) {
            throw new HorarioInvalidoException();
        }
        if (abre.isEmpty() || fecha.isEmpty()){
            throw new FormatoDeHoraInvalidoException();
        }
        if (!abre.matches("\\d{2}:\\d{2}") || !fecha.matches("\\d{2}:\\d{2}")) {
            throw new FormatoDeHoraInvalidoException();
        }
        try {
            int abreHour = Integer.parseInt(abre.split(":")[0]);
            int abreMinute = Integer.parseInt(abre.split(":")[1]);
            int fechaHour = Integer.parseInt(fecha.split(":")[0]);
            int fechaMinute = Integer.parseInt(fecha.split(":")[1]);
            if (abreHour > 23 || abreMinute > 59 || fechaHour > 23 || fechaMinute > 59) {
                throw new HorarioInvalidoException();
            }
            LocalTime abreTime = LocalTime.of(abreHour, abreMinute);
            LocalTime fechaTime = LocalTime.of(fechaHour, fechaMinute);
            if (abreTime.isAfter(fechaTime)) {
                throw new HorarioInvalidoException();
            }
        } catch (NumberFormatException e) {
            throw new FormatoDeHoraInvalidoException();
        }

        if (tipoMercado == null || tipoMercado.isEmpty() ||
                (!tipoMercado.equalsIgnoreCase("supermercado") &&
                        !tipoMercado.equalsIgnoreCase("minimercado") &&
                        !tipoMercado.equalsIgnoreCase("atacadista"))) {
            throw new TipoEmpresaInvalidoException();
        }

        for (Empresa empresa : empresas.values()) {
            if (empresa.getNome().equals(nome)) {
                if (empresa.getDono().getId() != donoId) {
                    throw new EmpresaComMesmoNomeDonoDiferenteException();
                }
                if (empresa.getDono().getId() == donoId && empresa.getEndereco().equals(endereco)) {
                    throw new EmpresaComMesmoNomeEEnderecoException();
                }
            }
        }
        Mercado novaEmpresa = new Mercado(nextEmpresaId++, nome, endereco, abre, fecha, tipoMercado, dono);
        empresas.put(novaEmpresa.getId(), novaEmpresa);
        salvarEmpresas();
        return novaEmpresa.getId();
    }
    public void alterarFuncionamento(int mercadoId, String abre, String fecha) throws Exception {
        // Busca a empresa com o ID fornecido
        Empresa empresa = findEmpresaById(mercadoId);

        // Verifica se a empresa é um mercado
        if (!(empresa instanceof Mercado)) {
            throw new IllegalArgumentException("Nao e um mercado valido");
        }

        // Cast seguro para Mercado
        Mercado mercado = (Mercado) empresa;

        // Validação de nulos
        if (abre == null || fecha == null) {
            throw new HorarioInvalidoException();
        }

        // Validação de strings vazias
        if (abre.isEmpty() || fecha.isEmpty()) {
            throw new FormatoDeHoraInvalidoException();
        }

        // Validação de formato (HH:mm)
        if (!abre.matches("\\d{2}:\\d{2}") || !fecha.matches("\\d{2}:\\d{2}")) {
            throw new FormatoDeHoraInvalidoException();
        }

        try {
            // Extrai horas e minutos
            int abreHour = Integer.parseInt(abre.split(":")[0]);
            int abreMinute = Integer.parseInt(abre.split(":")[1]);
            int fechaHour = Integer.parseInt(fecha.split(":")[0]);
            int fechaMinute = Integer.parseInt(fecha.split(":")[1]);

            // Validação de limites para horas e minutos
            if (abreHour > 23 || abreMinute > 59 || fechaHour > 23 || fechaMinute > 59) {
                throw new HorarioInvalidoException();
            }

            // Converte as strings para LocalTime
            LocalTime abreTime = LocalTime.of(abreHour, abreMinute);
            LocalTime fechaTime = LocalTime.of(fechaHour, fechaMinute);

            // Verifica se o horário de abertura é depois do de fechamento
            if (abreTime.isAfter(fechaTime)) {
                throw new HorarioInvalidoException();
            }

            // Atualiza os horários no objeto mercado
            mercado.setHorarioFuncionamento(abre, fecha);

            // Salva as alterações no repositório ou banco de dados
            salvarEmpresas();
        } catch (NumberFormatException e) {
            // Captura qualquer erro ao tentar converter as partes de horas e minutos
            throw new FormatoDeHoraInvalidoException();
        }
    }
    public void cadastrarEntregador(int empresaId, int entregadorId) throws Exception {
        Empresa empresa = empresas.get(empresaId);
        if (empresa == null) {
            throw new EmpresaNaoEncontradaException();
        }
        Usuario usuario = findUsuarioById(entregadorId);
        if (!(usuario instanceof UsuarioEntregador)) {
            throw new UsuarioNaoEUmEntregadorException();
        }
        UsuarioEntregador entregador = (UsuarioEntregador) usuario;
        if (entregador.getVeiculo() == null || entregador.getVeiculo().isEmpty()) {
            throw new VeiculoInvalidoException();
        }
        if (entregador.getPlaca() == null || entregador.getPlaca().isEmpty()) {
            throw new PlacaInvalidaException();
        }

        empresa.adicionarEntregador(entregador);
    }
    public List<String> getEntregadores(int empresaId) throws Exception {
        Empresa empresa = empresas.get(empresaId);
        if (empresa == null) {
            throw new EmpresaNaoEncontradaException();
        }
        return empresa.getEntregadores().stream()
                .map(UsuarioEntregador::getEmail)
                .collect(Collectors.toList());
    }
    public List<String> getEmpresas(int entregadorId) throws Exception {
        Usuario entregador = findUsuarioById(entregadorId);
        if (!(entregador instanceof UsuarioEntregador)) {
            throw new UsuarioNaoEUmEntregadorException();
        }
        return empresas.values().stream()
                .filter(empresa -> empresa.getEntregadores().contains(entregador))
                .map(empresa -> String.format("[%s, %s]", empresa.getNome(), empresa.getEndereco()))
                .collect(Collectors.toList());
    }
}