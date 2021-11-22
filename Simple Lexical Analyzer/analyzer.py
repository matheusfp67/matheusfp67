from typing import NamedTuple
import re

class Token(NamedTuple):
    type: str
    value: str

def tokenize(code):
    keywords = {'while', 'if', 'int', 'float', 'main'}
    token_specification = [
        ('variable', r'\d*[a-zA-Z][a-zA-Z0-9]*'),
        ('number',   r'\d+(\.\d*)?(?<![A-Za-z])'),
        ('comment',  r'[/*].*[*\]'),
        ('operator',       r'[+\-*/]'),    
        ('symbol',       r'[(){}]'),    
        ('SKIP',     r'[ \t]+'),       # Skip over spaces and tabs
    ]
    tok_regex = '|'.join('(?P<%s>%s)' % pair for pair in token_specification)

    for mo in re.finditer(tok_regex, code):
        kind = mo.lastgroup
        value = mo.group()
        if kind == 'variable' and value in keywords:
            kind = value
        elif kind == 'number':
            value = float(value) if '.' in value else int(value)
        #elif kind == 'operator':
            #kind = value
        elif kind == 'comment':
            continue
        elif kind == 'SKIP':
            continue
        yield Token(kind, value)

statements = '''
    int main(){
    
    while(teste){
    /**/
    123
    teste12
    12teste
    /*comentario*/
    }
'''

for token in tokenize(statements):
    print(token)