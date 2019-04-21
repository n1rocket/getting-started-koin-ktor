package org.koin.sample

interface ModelService {
    fun getModels(): Model
    fun getItemByKey(id:String): Item?
}

class ModelServiceImpl(val modelRepository: ModelRepository) : ModelService{
    override fun getModels(): Model = modelRepository.getModels()
    override fun getItemByKey(id: String): Item? = modelRepository.getItemByKey(id)
            //call.respond(HttpStatusCode.NotFound)
}