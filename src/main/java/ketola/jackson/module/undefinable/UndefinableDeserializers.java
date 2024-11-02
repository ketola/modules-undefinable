package ketola.jackson.module.undefinable;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ReferenceType;
import java.io.Serial;
import java.io.Serializable;

public class UndefinableDeserializers extends Deserializers.Base implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public JsonDeserializer<?> findReferenceDeserializer(ReferenceType refType, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer contentTypeDeserializer, JsonDeserializer<?> contentDeserializer) {
        if (refType.hasRawClass(Undefinable.class)) {
            return new UndefinableDeserializer(refType, null, contentTypeDeserializer, contentDeserializer);
        }
        return null;
    }
}
