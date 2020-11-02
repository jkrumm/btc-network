# btc-network
Author: Johannes Krumm | Source: https://github.com/jkrumm/btc-network

This repository is implementing a very simple Bitcoin wallet for demo purposes. 
It utilizes the library [bitcoinj](https://bitcoinj.org/) to communicate with the blockchain.

Below you will find instructions for running and using the application.

<br>

## Running the Spring Boot application
#### Setting up application.properties
Change the **bitcoin.file-location** inside the **application.properties** file to the path of the repository or any other path 
where you want to store the wallet and secrets.
        
    bitcoin.file-location=/Users/johanneskrumm/downloads/btc-network

<br>

#### Running the application
The application utilizes Maven therefore running it should work using:

    mvn spring-boot:run
    
Otherwise I recommend using IntelliJ IDEA to modify and built the application.

The build could take sometime because packages and also a small part of the Bitcoin blockchain need to be downloaded to enable the communication with it.

<br>

## Using the application
In the main folder of the repository you will find the **btc-network.postman_collection.json** file. If you import that into
Postman you will have all the endpoints already configured. 
<br>
<br>
#### Fetching wallet address and balance
To get the **wallet address** use the Postman collection or run:

    curl --location --request GET 'http://localhost:8080/address' \
    --header 'Cookie: csrf_token=ImNjZGI0OWU3ZjczODZiNjE2YjgwZjgxY2JiNDE0Zjk3Y2U0M2NkMjAi.DsGkvg.JYUlXnRgcqtR5z4_tiqKxzSMUjg'

Example Response:

    Wallet address: n2JxdYrdogmnvyiH1sWn2jfbPEUaTtY716

<br>
To get the **wallet balance** use the Postman collection or run:

    curl --location --request GET 'http://localhost:8080/balance' \
    --header 'Cookie: csrf_token=ImNjZGI0OWU3ZjczODZiNjE2YjgwZjgxY2JiNDE0Zjk3Y2U0M2NkMjAi.DsGkvg.JYUlXnRgcqtR5z4_tiqKxzSMUjg'

Example Response:

    0.0 BTC

<br>

#### Test receiving coins
To receive coins, we can simply **request from test faucet** like this https://bitcoinfaucet.uo1.net/ or https://coinfaucet.eu/en/btc-testnet/ or https://testnet-faucet.mempool.co/. 
Paste your wallet address and you should see following printed in your console. These test faucets aren't sending the transactions instantly. Although once they do you should instantly see it in the command line.

    commitTx of 74d846d60b7cc95de61e9a7be32eb61c3372cfe96589ce78a1647f5e25c5e65e
    Received tx for 0.00022 BTC
    Estimated balance is now: 0.00022 BTC

<br>

#### After confirmation
Once we have **at least 1 confirmation**, you should see this printed:

    Received tx 0.00022 BTC is confirmed. 
    Balance is now: 0.00022 BTC

At this point, we can already assume that our transaction is valid. **Check your new balance** using the Postman collection or run:

    curl --location --request GET 'http://localhost:8080/balance' \
    --header 'Cookie: csrf_token=ImNjZGI0OWU3ZjczODZiNjE2YjgwZjgxY2JiNDE0Zjk3Y2U0M2NkMjAi.DsGkvg.JYUlXnRgcqtR5z4_tiqKxzSMUjg'

Example response:

    0.00022 BTC

<br>

#### Test sending coins
The GET endpoint /send uses following PathParamters to configure amount and target address. Make sure the amount is less than your balance.

     http://localhost:8080/send?amount={amount}&address={address_to_send_to}

Once again you can use the Postman collection to **send coins** or run following:

    curl --location --request GET 'http://localhost:8080/send?amount=0.00001&address=mumMe9wWeLb26oGKf334CKiRYiWMpmKpUp' \
    --header 'Cookie: csrf_token=ImNjZGI0OWU3ZjczODZiNjE2YjgwZjgxY2JiNDE0Zjk3Y2U0M2NkMjAi.DsGkvg.JYUlXnRgcqtR5z4_tiqKxzSMUjg'

Example response:

    Sent 0.00001 BTC to mumMe9wWeLb26oGKf334CKiRYiWMpmKpUp

On the command line you should now **receive the transaction hash**:

    Sent coins onwards! Transaction hash is 76968570ecf2feb57d58dd41cea891e2ea7927cf784cdebbe49986e7d93631ce
    
To check the **details of this transaction**, you can go to https://live.blockcypher.com/btc-testnet/ and paste the hash in the search bar.
There you will find all details about the transaction as well as confidence and confirmations. 