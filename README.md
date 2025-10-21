# 🎬 Movie Explorer - Seu App de Filmes

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-orange.svg)](https://developer.android.com/jetpack/compose)

## 📱 Sobre o Projeto

**Movie Explorer** é um aplicativo Android desenvolvido em **Kotlin** com **Jetpack Compose** que consome a API do **OMDb (Open Movie Database)** para buscar e exibir informações sobre filmes, similar ao IMDb.

O app permite buscar filmes por título, visualizar pôsteres e informações básicas, e explorar detalhes completos como sinopse, elenco, diretor, avaliações e muito mais.

## ✨ Funcionalidades

- 🔍 **Busca de Filmes**: Pesquise por qualquer título de filme
- 🎭 **Lista de Resultados**: Visualize resultados com pôsteres e informações básicas  
- 📖 **Detalhes Completos**: Veja sinopse, elenco, diretor, avaliação IMDb e mais
- 🎨 **Interface Moderna**: Design Material 3 com modo claro/escuro automático
- ⚡ **Performance**: Carregamento assíncrono de imagens com Coil
- 🔄 **Estados Reativos**: Interface reativa que responde automaticamente a mudanças

## 🛠 Tecnologias Utilizadas

| Tecnologia | Descrição |
|------------|-----------|
| **Kotlin** | Linguagem de programação principal |
| **Jetpack Compose** | Framework de UI moderna e declarativa |
| **Material 3** | Sistema de design do Google |
| **Retrofit** | Cliente HTTP para consumo da API REST |
| **Coil Compose** | Carregamento e cache de imagens |
| **ViewModel** | Gerenciamento de estado e ciclo de vida |
| **Coroutines** | Programação assíncrona |
| **GSON** | Serialização/deserialização JSON |

## 🏗 Arquitetura

O projeto segue padrões de arquitetura Android modernos:

```
app/src/main/java/com/movieexplorer/
├── data/                 # Modelos de dados
│   └── Movie.kt         # Data classes (Movie, MovieSearchResponse, MovieDetails)
├── network/             # Camada de rede
│   ├── MovieApi.kt      # Interface da API
│   └── RetrofitClient.kt # Configuração do Retrofit
├── ui/                  # Interface do usuário
│   ├── theme/          # Tema Material 3
│   ├── MainScreen.kt   # Tela principal
│   ├── SearchBar.kt    # Componente de busca
│   ├── MovieCard.kt    # Card de filme
│   ├── MovieList.kt    # Lista de filmes
│   └── MovieDetailsScreen.kt # Tela de detalhes
├── viewmodel/          # Lógica de negócio
│   └── MovieViewModel.kt # ViewModel principal
└── MainActivity.kt     # Atividade principal
```

## 🚀 Como Executar

### Pré-requisitos

1. **Android Studio** (versão mais recente)
2. **Chave da API OMDb** (gratuita)

### Configuração da API

1. Acesse [https://www.omdbapi.com/apikey.aspx](https://www.omdbapi.com/apikey.aspx)
2. Solicite sua chave gratuita (chegará por e-mail)
3. Abra o arquivo `app/src/main/java/com/movieexplorer/network/RetrofitClient.kt`
4. Substitua `YOUR_API_KEY` pela sua chave:

```kotlin
private const val API_KEY = "sua_chave_aqui"
```

### Executando o App

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/movie-explorer-app.git
```

2. Abra o projeto no Android Studio

3. Configure sua API key (passo anterior)

4. Sincronize o projeto (Gradle Sync)

5. Execute no dispositivo ou emulador

## 📱 Screenshots

### Tela Principal
- Interface limpa com campo de busca
- Estado inicial com mensagem de boas-vindas
- Lista de resultados com pôsteres e informações básicas

### Tela de Detalhes
- Pôster em alta qualidade
- Informações completas do filme
- Avaliação IMDb com ícone de estrela
- Sinopse e detalhes técnicos

### Estados da Interface
- **Loading**: Indicador de carregamento durante buscas
- **Erro**: Mensagens de erro amigáveis para problemas de conexão
- **Vazio**: Orientação quando não há resultados

## 🎯 Recursos Implementados

### ✅ Requisitos Obrigatórios
- [x] Consumo da API OMDb com Retrofit
- [x] Interface em Jetpack Compose
- [x] Estados reativos com `mutableStateOf`
- [x] ViewModel para gerenciamento de estado
- [x] Busca por título de filme
- [x] Exibição de pôsteres com Coil
- [x] Lista de resultados
- [x] Tratamento de erros

### 🌟 Recursos Extras (Bônus)
- [x] Tela de detalhes completos do filme
- [x] Modo claro/escuro automático (Material 3)
- [x] Animações de carregamento
- [x] Tratamento robusto de erros
- [x] Interface responsiva e moderna
- [x] Estados de carregamento diferenciados

## 🧪 Testando o App

### Exemplos de Busca
- `batman` - Filmes do Batman
- `avengers` - Filmes dos Vingadores  
- `star wars` - Saga Star Wars
- `matrix` - Trilogia Matrix

### Casos de Teste
- Busca com resultados múltiplos
- Busca sem resultados
- Busca com erro de rede
- Visualização de detalhes
- Navegação entre telas

## 📚 Conceitos Demonstrados

| Conceito | Implementação |
|----------|---------------|
| **Jetpack Compose** | Interface declarativa com Composables |
| **State Management** | Estados reativos com `mutableStateOf` |
| **MVVM Architecture** | ViewModel separando UI da lógica |
| **API Consumption** | Retrofit + Coroutines para chamadas REST |
| **Image Loading** | Coil para carregamento assíncrono |
| **Error Handling** | Tratamento de exceções e estados de erro |
| **Material Design** | Componentes Material 3 |
| **Navigation** | Navegação entre telas com estado |

## 👥 Autores

**[Seu Nome Aqui]** - Desenvolvedor Principal
- GitHub: [@seu-usuario](https://github.com/seu-usuario)
- Email: seu.email@exemplo.com

**[Nome do Parceiro]** - Desenvolvedor (se em dupla)
- GitHub: [@parceiro-usuario](https://github.com/parceiro-usuario)
- Email: parceiro.email@exemplo.com

## 📄 Licença

Este projeto foi desenvolvido como parte do curso de desenvolvimento Android e está licenciado sob a [MIT License](LICENSE).

## 🙏 Agradecimentos

- [OMDb API](https://www.omdbapi.com/) pela API gratuita de filmes
- [Material Design](https://material.io/) pelas diretrizes de design
- [Android Developers](https://developer.android.com/) pela documentação

---

⭐ **Desenvolvido com ❤️ para o projeto final de Android Development**
