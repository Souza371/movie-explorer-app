package com.movieexplorer.util

/**
 * Utilitário para tradução de termos da API OMDb para português brasileiro.
 * 
 * Responsável por:
 * - Traduzir gêneros de filmes
 * - Traduzir classificações etárias
 * - Traduzir idiomas
 * - Traduzir países
 * - Traduzir labels técnicos
 * 
 * @author Vicente
 * @since 1.1
 */
object TranslationUtils {

    /**
     * Traduz gêneros de filmes do inglês para português
     */
    fun translateGenre(genre: String?): String {
        if (genre.isNullOrBlank()) return "Não informado"
        
        return genre.split(", ").joinToString(", ") { singleGenre ->
            when (singleGenre.trim().lowercase()) {
                "action" -> "Ação"
                "adventure" -> "Aventura"
                "animation" -> "Animação"
                "biography" -> "Biografia"
                "comedy" -> "Comédia"
                "crime" -> "Crime"
                "documentary" -> "Documentário"
                "drama" -> "Drama"
                "family" -> "Família"
                "fantasy" -> "Fantasia"
                "film-noir" -> "Film-Noir"
                "history" -> "História"
                "horror" -> "Terror"
                "music" -> "Musical"
                "musical" -> "Musical"
                "mystery" -> "Mistério"
                "romance" -> "Romance"
                "sci-fi" -> "Ficção Científica"
                "science fiction" -> "Ficção Científica"
                "sport" -> "Esporte"
                "thriller" -> "Suspense"
                "war" -> "Guerra"
                "western" -> "Faroeste"
                else -> singleGenre.capitalizeWords()
            }
        }
    }

    /**
     * Traduz classificações etárias do inglês para português
     */
    fun translateRating(rating: String?): String {
        if (rating.isNullOrBlank()) return "Não informado"
        
        return when (rating.trim().uppercase()) {
            "G" -> "Livre"
            "PG" -> "10 anos"
            "PG-13" -> "13 anos"
            "R" -> "16 anos"
            "NC-17" -> "18 anos"
            "NOT RATED" -> "Não classificado"
            "N/A" -> "Não informado"
            "APPROVED" -> "Aprovado"
            "UNRATED" -> "Sem classificação"
            else -> rating
        }
    }

    /**
     * Traduz idiomas do inglês para português
     */
    fun translateLanguage(language: String?): String {
        if (language.isNullOrBlank()) return "Não informado"
        
        return language.split(", ").joinToString(", ") { singleLanguage ->
            when (singleLanguage.trim().lowercase()) {
                "english" -> "Inglês"
                "portuguese" -> "Português"
                "spanish" -> "Espanhol"
                "french" -> "Francês"
                "german" -> "Alemão"
                "italian" -> "Italiano"
                "japanese" -> "Japonês"
                "chinese" -> "Chinês"
                "mandarin" -> "Mandarim"
                "korean" -> "Coreano"
                "russian" -> "Russo"
                "arabic" -> "Árabe"
                "hindi" -> "Hindi"
                "dutch" -> "Holandês"
                "swedish" -> "Sueco"
                "norwegian" -> "Norueguês"
                "danish" -> "Dinamarquês"
                "finnish" -> "Finlandês"
                else -> singleLanguage.capitalizeWords()
            }
        }
    }

    /**
     * Traduz países do inglês para português
     */
    fun translateCountry(country: String?): String {
        if (country.isNullOrBlank()) return "Não informado"
        
        return country.split(", ").joinToString(", ") { singleCountry ->
            when (singleCountry.trim().lowercase()) {
                "usa" -> "Estados Unidos"
                "united states" -> "Estados Unidos"
                "uk" -> "Reino Unido"
                "united kingdom" -> "Reino Unido"
                "canada" -> "Canadá"
                "australia" -> "Austrália"
                "germany" -> "Alemanha"
                "france" -> "França"
                "spain" -> "Espanha"
                "italy" -> "Itália"
                "japan" -> "Japão"
                "china" -> "China"
                "south korea" -> "Coreia do Sul"
                "brazil" -> "Brasil"
                "mexico" -> "México"
                "argentina" -> "Argentina"
                "russia" -> "Rússia"
                "india" -> "Índia"
                "sweden" -> "Suécia"
                "norway" -> "Noruega"
                "denmark" -> "Dinamarca"
                "netherlands" -> "Holanda"
                "belgium" -> "Bélgica"
                "switzerland" -> "Suíça"
                "austria" -> "Áustria"
                else -> singleCountry.capitalizeWords()
            }
        }
    }

    /**
     * Traduz labels técnicos para português
     */
    fun translateLabel(label: String): String {
        return when (label.lowercase()) {
            "director" -> "Diretor"
            "writer" -> "Roteirista"
            "actors" -> "Elenco"
            "genre" -> "Gênero"
            "language" -> "Idioma"
            "country" -> "País"
            "awards" -> "Prêmios"
            "runtime" -> "Duração"
            "released" -> "Lançamento"
            "rated" -> "Classificação"
            "plot" -> "Sinopse"
            "imdb rating" -> "Nota IMDb"
            else -> label
        }
    }

    /**
     * Traduz duração para formato brasileiro
     */
    fun translateRuntime(runtime: String?): String {
        if (runtime.isNullOrBlank()) return "Não informado"
        
        val timeRegex = Regex("(\\d+)\\s*min")
        val match = timeRegex.find(runtime)
        
        return if (match != null) {
            val minutes = match.groupValues[1].toInt()
            val hours = minutes / 60
            val remainingMinutes = minutes % 60
            
            when {
                hours > 0 && remainingMinutes > 0 -> "${hours}h ${remainingMinutes}min"
                hours > 0 -> "${hours}h"
                else -> "${minutes}min"
            }
        } else {
            runtime
        }
    }

    /**
     * Traduz prêmios para português
     */
    fun translateAwards(awards: String?): String {
        if (awards.isNullOrBlank() || awards == "N/A") return "Nenhum prêmio registrado"
        
        return awards
            .replace("Won", "Ganhou")
            .replace("Nominated for", "Indicado para")
            .replace("Oscar", "Oscar")
            .replace("Golden Globe", "Globo de Ouro")
            .replace("Emmy", "Emmy")
            .replace("BAFTA", "BAFTA")
            .replace("another", "outro")
            .replace("wins", "vitórias")
            .replace("nominations", "indicações")
            .replace(" win.", " vitória.")
            .replace(" nomination.", " indicação.")
    }

    /**
     * Capitaliza palavras corretamente
     */
    private fun String.capitalizeWords(): String {
        return this.split(" ").joinToString(" ") { word ->
            word.lowercase().replaceFirstChar { 
                if (it.isLowerCase()) it.titlecase() else it.toString() 
            }
        }
    }
}