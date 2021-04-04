package id.buaja.paging3.domain.usecase

import id.buaja.paging3.domain.repository.CharacterRepository

/**
 * Created by Julsapargi Nursam on 4/4/21.
 */

class CharacterUseCaseImpl(private val characterRepository: CharacterRepository): CharacterUseCase {
    override fun getCharacter() = characterRepository.getCharacter()
}