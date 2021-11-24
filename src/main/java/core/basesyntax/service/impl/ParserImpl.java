package core.basesyntax.service.impl;

import core.basesyntax.model.TransactionDto;
import core.basesyntax.service.Parser;
import core.basesyntax.service.Validator;

import java.util.ArrayList;
import java.util.List;

public class ParserImpl implements Parser {
    private static final int TYPE_OF_HANDLER = 0;
    private static final int KIND_OF_FRUIT = 1;
    private static final int QUANTITY_OF_FRUIT = 2;
    private Validator validator;

    public ParserImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public List<TransactionDto> parseLine(List<String> inputData) {
       validator.isValid(inputData);
       List<TransactionDto> resultList = new ArrayList<>();
       inputData.stream()
               .filter(e -> !e.equals("type,fruit,quantity"))
               .map(e -> e.split(","))
               .forEach(e -> resultList.add(new TransactionDto(e[0], e[1], Integer.parseInt(e[2]))));
       return resultList;
    }
}