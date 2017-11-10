<?php
use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

require '../vendor/autoload.php';

$config['displayErrorDetails'] = true;
$config['addContentLengthHeader'] = false;
$config['db']['host'] = "localhost";
$config['db']['port'] = "3306";
$config['db']['dbname'] = "servicodb";
$config['db']['user'] = "root";
$config['db']['pass'] = "";

$app = new \Slim\App (["config" => $config]);
$container = $app->getContainer();

$container['db'] = function ($c) { $dbConfig = $c['config']['db'];
    $pdo = new PDO("mysql:host=" . $dbConfig['host'] .";port=".$dbConfig['port'] .";dbname=".$dbConfig['dbname'],$dbConfig['user'], $dbConfig['pass']);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $pdo->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
    $db = new NotORM($pdo);
    return $db;
};
//$this->db;

function authUser($db){
    $dataUser = $db->usuariodb();
    $users = array();

    foreach($dataUser as $user){
            $users[$user['usuario']]=$user["senha"];
    }
        return $users;
}


$app->add(new Tuupola\Middleware\HttpBasicAuthentication(
     [ "users" => authUser($container['db']) ]
));
$app->get("/api/{nome}", function (Request $request, Response $response) {
    $nome = $request->getAttribute("nome");
    $response->getBody()->write("Bem vindo a API Basic Auth, $nome");
    return $response;
});


$app->get('/hello/{nome}',
    function (Request $request, Response $response) {
    $nome = $request->getAttribute('nome');
    $response->getBody()->write("Bem vindo a API, $nome");
    return $response;
});

$app->put('/edit/{id}',
    function(Request $request, Response $response){
        $dataEnt = $request->getParsedBody();
        $id = $request->getAttribute('id');
        $sql = $this->db->entregadb()->where("id=?",$id)->fetch();
        $newEntrega = array(
            "nomeRecebidor" => $dataEnt["cpfRecebidor"],
            "cpfRecebidor" => $dataEnt["nomeRecebidor"],
            "dataHoraEntrega" => $dataEnt["dataHoraEntrega"]
        );
        $updated = $sql->update($newEntrega);
        if( $updated!==null) echo "ok";
        else echo $response->withStatus(404);

 });

$app->delete('/delete/{id}', function(Request $request, Response $response){
    $id = $request->getAttribute('id');
    $sql=null;
    $sql= $this->db->entregadb()->where("id=?",$id)->fetch()->delete();
    if($sql!==null) {echo "ok";}
    else $response->withStatus(404);
 });

$app->run();
?>