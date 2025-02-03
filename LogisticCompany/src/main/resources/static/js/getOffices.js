async function getOffices()
{
    const url = 'http://localhost:8080/api/offices';

    const reposnse = await fetch(url);

    if(!reposnse.ok){
        const message = `An error has occured: ${reposnse.status}`;
        throw new Error(message);
    }

    const offices = await reposnse.json();
    console.log(offices);
}