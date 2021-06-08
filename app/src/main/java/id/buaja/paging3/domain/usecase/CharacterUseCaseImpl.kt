package id.buaja.paging3.domain.usecase

import id.buaja.paging3.domain.repository.CharacterRepository
import javax.inject.Inject

/**
 * Created by Julsapargi Nursam on 4/4/21.
 */

class CharacterUseCaseImpl @Inject constructor(private val characterRepository: CharacterRepository) :
    CharacterUseCase {
    override fun getCharacter() = characterRepository.getCharacter()
}