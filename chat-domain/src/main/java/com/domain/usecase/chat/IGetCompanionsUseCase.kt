package com.domain.usecase.chat

import com.domain.model.UserUI

interface IGetCompanionsUseCase {

    suspend operator fun invoke(): List<UserUI>

}