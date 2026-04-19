package com.stevecoder.managementApp.common.mediator;

public interface RequestHandler<T extends Request<R>, R> {

    R handle(T request);

    // En este caso creo el producto pasado de productDTO -> CreateProductRequest -> Product. Y se lo paso
    // al repositorio.
    Class<T> getRequestType();

}
