package com.commodities.common;

import org.datanucleus.query.typesafe.*;
import org.datanucleus.api.jdo.query.*;

public class QCommodityData extends org.datanucleus.api.jdo.query.PersistableExpressionImpl<CommodityData> implements PersistableExpression<CommodityData>
{
    public static final QCommodityData jdoCandidate = candidate("this");

    public static QCommodityData candidate(String name)
    {
        return new QCommodityData(null, name, 5);
    }

    public static QCommodityData candidate()
    {
        return jdoCandidate;
    }

    public static QCommodityData parameter(String name)
    {
        return new QCommodityData(CommodityData.class, name, ExpressionType.PARAMETER);
    }

    public static QCommodityData variable(String name)
    {
        return new QCommodityData(CommodityData.class, name, ExpressionType.VARIABLE);
    }

    public final NumericExpression<Long> id;
    public final StringExpression name;
    public final DateTimeExpression date;
    public final StringExpression price;
    public final StringExpression type;

    public QCommodityData(PersistableExpression parent, String name, int depth)
    {
        super(parent, name);
        this.id = new NumericExpressionImpl<Long>(this, "id");
        this.name = new StringExpressionImpl(this, "name");
        this.date = new DateTimeExpressionImpl(this, "date");
        this.price = new StringExpressionImpl(this, "price");
        this.type = new StringExpressionImpl(this, "type");
    }

    public QCommodityData(Class type, String name, org.datanucleus.api.jdo.query.ExpressionType exprType)
    {
        super(type, name, exprType);
        this.id = new NumericExpressionImpl<Long>(this, "id");
        this.name = new StringExpressionImpl(this, "name");
        this.date = new DateTimeExpressionImpl(this, "date");
        this.price = new StringExpressionImpl(this, "price");
        this.type = new StringExpressionImpl(this, "type");
    }
}
