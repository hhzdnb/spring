package com.vesus.audit;

/**
 * 审计操作对象类别枚举
 */
public enum AuditOperationType {
    INSERT((byte) 0) {
        @Override
        public String getValue() {
            return "新增";
        }
    }, UPDATE((byte) 1) {
        @Override
        public String getValue() {
            return "修改";
        }
    }, DELETE((byte) 2) {
        @Override
        public String getValue() {
            return "删除";
        }
    };

    private String value;
    private Byte representation;

    AuditOperationType(byte representation) {
        this.representation = representation;
    }

    AuditOperationType() {
        value = toString();
    }

    public static AuditOperationType parse(final String value) {
        AuditOperationType operation = null;
        for (final AuditOperationType op : AuditOperationType.values()) {
            if (op.getValue().equals(value)) {
                operation = op;
                break;
            }
        }
        return operation;
    }

    public static AuditOperationType parseNumber(final Object representation) {
        System.out.println(representation.getClass().getName());
        if (representation == null || !(representation instanceof Byte)) {
            return null;
        }
        switch ((Byte) representation) {
            case 0: {
                return INSERT;
            }
            case 1: {
                return UPDATE;
            }
            case 2: {
                return DELETE;
            }
            default: {
                throw new IllegalArgumentException("Unknown representation: " + representation);
            }
        }
    }

    public String getValue() {
        return value;
    }

    public Byte getRepresentation() {
        return representation;
    }
}