package ketola.jackson.module.undefinable;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.Serial;

public class UndefinableDeserializer extends ReferenceTypeDeserializer<Undefinable<?>> {

    @Serial
    private static final long serialVersionUID = 1L;

    public UndefinableDeserializer(JavaType fullType, ValueInstantiator inst, TypeDeserializer typeDeser,
                                   JsonDeserializer<?> deser) {
        super(fullType, inst, typeDeser, deser);
    }

    public UndefinableDeserializer withResolved(TypeDeserializer typeDeser, JsonDeserializer<?> valueDeser) {
        return new UndefinableDeserializer(this._fullType, this._valueInstantiator, typeDeser, valueDeser);
    }

    public Undefinable<?> getNullValue(DeserializationContext ctxt) throws JsonMappingException {
        return new Undefinable<>(this._valueDeserializer.getNullValue(ctxt));
    }

    @Override
    public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
        return this.getNullValue(ctxt);
    }

    @Override
    public Object getAbsentValue(DeserializationContext ctxt) {
        return Undefinable.Undefined.UNDEFINED;
    }

    public Undefinable<?> referenceValue(Object contents) {
        return new Undefinable<>(contents);
    }

    public Object getReferenced(Undefinable<?> reference) {
        return reference;
    }

    public Undefinable<?> updateReference(Undefinable<?> reference, Object contents) {
        return new Undefinable<>(contents);
    }
}
