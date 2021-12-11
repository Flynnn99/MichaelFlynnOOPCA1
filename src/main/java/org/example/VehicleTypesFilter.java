package org.example;

public class VehicleTypesFilter implements IFilter
{
    private String type;

    public VehicleTypesFilter(String type)
    {
        this.type = type;

    }

    @Override
    public boolean matches(Object other)
    {
        Vehicle v = (Vehicle) other;
        // cast from Object to Product
        return v.getType().equalsIgnoreCase(type);
    }
}
