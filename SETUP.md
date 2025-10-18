# 📋 Instruções de Configuração - Movie Explorer

## ⚠️ IMPORTANTE: Configuração da API Key

Antes de executar o aplicativo, você **DEVE** configurar sua chave da API OMDb:

### 1. Obtenha sua Chave da API

1. Acesse: https://www.omdbapi.com/apikey.aspx
2. Preencha o formulário:
   - **Email**: seu email válido
   - **Account Type**: FREE! (1,000 daily limit)
   - **First Name**: seu nome
   - **Last Name**: seu sobrenome
   - **Intended Use**: Educational/Learning Project
3. Clique em **"Submit"**
4. Verifique seu email e confirme a solicitação
5. Você receberá sua API key por email

### 2. Configure no Projeto

1. Abra o arquivo: `app/src/main/java/com/movieexplorer/viewmodel/MovieViewModel.kt`

2. Encontre esta linha:
```kotlin
private val apiKey = "YOUR_API_KEY_HERE" // ⚠️ SUBSTITUA PELA SUA CHAVE
```

3. Substitua `YOUR_API_KEY_HERE` pela sua chave recebida por email:
```kotlin
private val apiKey = "12345678" // Sua chave aqui
```

### 3. Teste a Configuração

1. Execute o app no Android Studio
2. Digite "batman" no campo de busca
3. Se aparecer uma lista de filmes, está funcionando!
4. Se aparecer erro sobre API key, verifique se configurou corretamente

## 🚀 Executando o Projeto

### Pré-requisitos
- Android Studio (última versão)
- JDK 8 ou superior
- Conexão com internet

### Passos

1. **Clone o repositório**:
```bash
git clone <seu-repositorio>
cd movie-explorer-app
```

2. **Abra no Android Studio**:
   - File → Open
   - Selecione a pasta do projeto
   - Aguarde a sincronização do Gradle

3. **Configure a API Key** (passo anterior)

4. **Execute o app**:
   - Conecte um dispositivo Android ou inicie um emulador
   - Clique no botão "Run" (▶️) ou pressione Shift+F10

## 🛠 Estrutura do Projeto

```
movie-explorer-app/
├── app/
│   ├── build.gradle                 # Dependências do app
│   └── src/main/
│       ├── AndroidManifest.xml      # Configurações do app
│       ├── java/com/movieexplorer/
│       │   ├── MainActivity.kt      # Atividade principal
│       │   ├── data/
│       │   │   └── Movie.kt         # Modelos de dados
│       │   ├── network/
│       │   │   ├── MovieApi.kt      # Interface da API
│       │   │   └── RetrofitClient.kt # Cliente HTTP
│       │   ├── ui/
│       │   │   ├── MainScreen.kt    # Tela principal
│       │   │   ├── SearchBar.kt     # Barra de busca
│       │   │   ├── MovieCard.kt     # Card do filme
│       │   │   ├── MovieList.kt     # Lista de filmes
│       │   │   ├── MovieDetailsScreen.kt # Detalhes
│       │   │   └── theme/           # Tema Material 3
│       │   └── viewmodel/
│       │       └── MovieViewModel.kt # ViewModel (Configure aqui!)
│       └── res/                     # Recursos (strings, etc.)
├── build.gradle                     # Configurações globais
└── README.md                        # Documentação
```

## 🎯 Funcionalidades

### Busca de Filmes
- Digite qualquer título de filme na barra de pesquisa
- Pressione "Buscar" ou Enter
- Veja a lista de resultados com pôsteres

### Detalhes do Filme
- Toque em qualquer filme da lista
- Veja informações completas:
  - Sinopse
  - Elenco e diretor
  - Avaliação IMDb
  - Ano, duração, gênero
  - E muito mais!

### Interface
- Design moderno com Material 3
- Modo claro/escuro automático
- Animações de carregamento
- Mensagens de erro amigáveis

## 🐛 Solução de Problemas

### "Erro na busca" ou "Configure sua chave da API"
- Verifique se configurou a API key corretamente no `MovieViewModel.kt`
- Certifique-se que a chave não tem espaços extras

### "Erro de conexão"
- Verifique sua conexão com internet
- Teste com outro filme (às vezes um título específico pode não existir)

### App não compila
- No Android Studio: Build → Clean Project
- Depois: Build → Rebuild Project

### Gradle Sync falha
- File → Invalidate Caches and Restart
- Aguarde download das dependências

## 📱 Testando

### Buscas Sugeridas
- `batman` → Vários filmes do Batman
- `avengers` → Filmes dos Vingadores
- `matrix` → Trilogia Matrix
- `star wars` → Saga completa
- `marvel` → Filmes da Marvel

### Casos de Teste
- Busca normal (deve retornar lista)
- Busca sem resultados (ex: "xyzabc123")
- Sem conexão (desative WiFi/dados)
- Tocar nos filmes (deve abrir detalhes)

## ✅ Checklist Final

- [ ] API Key configurada no `MovieViewModel.kt`
- [ ] Projeto compila sem erros
- [ ] Consegue buscar filmes
- [ ] Pôsteres carregam corretamente
- [ ] Detalhes dos filmes funcionam
- [ ] Interface está responsiva
- [ ] Estados de loading aparecem

---

🎉 **Pronto! Seu Movie Explorer está funcionando!**

Para dúvidas, consulte o README.md principal ou a documentação do projeto.