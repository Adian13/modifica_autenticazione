package it.unisa.c07.biblionet.utils.eventchangeinterceptor;

import java.util.function.Consumer;

import it.unisa.c07.biblionet.model.entity.Evento;

/**
 * Interfaccia.
 */
public interface OnEventChangeConsumer extends Consumer<Evento> { }
