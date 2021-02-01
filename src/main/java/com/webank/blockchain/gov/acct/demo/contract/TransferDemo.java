package com.webank.blockchain.gov.acct.demo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionEncoder;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class TransferDemo extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50604051604080610c728339810180604052810190808051906020019092919080519060200190929190505050600082600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fb47e016336040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15801561013d57600080fd5b505af1158015610151573d6000803e3d6000fd5b505050506040513d602081101561016757600080fd5b81019080805190602001909291905050509050816000808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550505050610aa3806101cf6000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063a9059cbb14610051578063e3d670d7146100b6575b600080fd5b34801561005d57600080fd5b5061009c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061010d565b604051808215151515815260200191505060405180910390f35b3480156100c257600080fd5b506100f7600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610824565b6040518082815260200191505060405180910390f35b600080600080600033600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ff869838826040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156101d357600080fd5b505af11580156101e7573d6000803e3d6000fd5b505050506040513d60208110156101fd57600080fd5b81019080805190602001909291905050501515610282576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f4163636f756e742069732061626e6f726d616c2100000000000000000000000081525060200191505060405180910390fd5b87600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ff869838826040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15801561034057600080fd5b505af1158015610354573d6000803e3d6000fd5b505050506040513d602081101561036a57600080fd5b810190808051906020019092919050505015156103ef576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f4163636f756e742069732061626e6f726d616c2100000000000000000000000081525060200191505060405180910390fd5b888073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141580156104595750600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614155b15156104f3576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260228152602001807f43616e2774207472616e7366657220746f20696c6c6567616c2061646472657381526020017f732100000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fb47e016336040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156105b057600080fd5b505af11580156105c4573d6000803e3d6000fd5b505050506040513d60208110156105da57600080fd5b8101908080519060200190929190505050965061063e896000808a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461096390919063ffffffff16565b9550856000808973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fb47e0168b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15801561074057600080fd5b505af1158015610754573d6000803e3d6000fd5b505050506040513d602081101561076a57600080fd5b810190808051906020019092919050505094506107ce896000808873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546109ed90919063ffffffff16565b9350836000808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550600197505050505050505092915050565b6000806000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fb47e016856040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156108e657600080fd5b505af11580156108fa573d6000803e3d6000fd5b505050506040513d602081101561091057600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b6000808383111515156109de576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601e8152602001807f536166654d6174683a207375627472616374696f6e206f766572666c6f77000081525060200191505060405180910390fd5b82840390508091505092915050565b6000808284019050838110151515610a6d576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f536166654d6174683a206164646974696f6e206f766572666c6f77000000000081525060200191505060405180910390fd5b80915050929150505600a165627a7a723058205a7b9f44108f778effe411055f48a04a2f0e7ece26eabe5a064341330dcabec40029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50604051604080610c728339810180604052810190808051906020019092919080519060200190929190505050600082600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a5d51f74336040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15801561013d57600080fd5b505af1158015610151573d6000803e3d6000fd5b505050506040513d602081101561016757600080fd5b81019080805190602001909291905050509050816000808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550505050610aa3806101cf6000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680636904e965146100515780639d4834e5146100b6575b600080fd5b34801561005d57600080fd5b5061009c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061010d565b604051808215151515815260200191505060405180910390f35b3480156100c257600080fd5b506100f7600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610824565b6040518082815260200191505060405180910390f35b600080600080600033600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ba013712826040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156101d357600080fd5b505af11580156101e7573d6000803e3d6000fd5b505050506040513d60208110156101fd57600080fd5b81019080805190602001909291905050501515610282576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f4163636f756e742069732061626e6f726d616c2100000000000000000000000081525060200191505060405180910390fd5b87600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ba013712826040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15801561034057600080fd5b505af1158015610354573d6000803e3d6000fd5b505050506040513d602081101561036a57600080fd5b810190808051906020019092919050505015156103ef576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260148152602001807f4163636f756e742069732061626e6f726d616c2100000000000000000000000081525060200191505060405180910390fd5b888073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141580156104595750600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614155b15156104f3576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260228152602001807f43616e2774207472616e7366657220746f20696c6c6567616c2061646472657381526020017f732100000000000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a5d51f74336040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156105b057600080fd5b505af11580156105c4573d6000803e3d6000fd5b505050506040513d60208110156105da57600080fd5b8101908080519060200190929190505050965061063e896000808a73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461096390919063ffffffff16565b9550856000808973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a5d51f748b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15801561074057600080fd5b505af1158015610754573d6000803e3d6000fd5b505050506040513d602081101561076a57600080fd5b810190808051906020019092919050505094506107ce896000808873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546109ed90919063ffffffff16565b9350836000808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550600197505050505050505092915050565b6000806000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a5d51f74856040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156108e657600080fd5b505af11580156108fa573d6000803e3d6000fd5b505050506040513d602081101561091057600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b6000808383111515156109de576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252601e8152602001807f536166654d6174683a207375627472616374696f6e206f766572666c6f77000081525060200191505060405180910390fd5b82840390508091505092915050565b6000808284019050838110151515610a6d576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252601b8152602001807f536166654d6174683a206164646974696f6e206f766572666c6f77000000000081525060200191505060405180910390fd5b80915050929150505600a165627a7a723058202c7ee2740f69e76659ded9940c24770df50dc97bacaaeb98e8841887e25a234d0029"};

    public static final String SM_BINARY = String.join("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"toAddress\",\"type\":\"address\"},{\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"transfer\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"owner\",\"type\":\"address\"}],\"name\":\"balance\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"accountManager\",\"type\":\"address\"},{\"name\":\"initBalance\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_BALANCE = "balance";

    protected TransferDemo(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt transfer(String toAddress, BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(toAddress), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void transfer(String toAddress, BigInteger value, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(toAddress), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForTransfer(String toAddress, BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(toAddress), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, BigInteger> getTransferInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple1<Boolean> getTransferOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_TRANSFER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public BigInteger balance(String owner) throws ContractException {
        final Function function = new Function(FUNC_BALANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public static TransferDemo load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new TransferDemo(contractAddress, client, credential);
    }

    public static TransferDemo deploy(Client client, CryptoKeyPair credential, String accountManager, BigInteger initBalance) throws ContractException {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(accountManager), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(initBalance)));
        return deploy(TransferDemo.class, client, credential, getBinary(client.getCryptoSuite()), encodedConstructor);
    }
}
