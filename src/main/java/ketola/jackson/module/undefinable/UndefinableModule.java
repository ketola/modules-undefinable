package ketola.jackson.module.undefinable;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.Module;

public class UndefinableModule extends Module {

    @Override
    public void setupModule(SetupContext context) {
        context.addDeserializers(new UndefinableDeserializers());
        context.addTypeModifier(new UndefinableTypeModifier());
    }

    @Override
    public Version version() {
        return PackageVersion.VERSION;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String getModuleName() {
        return "UndefinableModule";
    }
}
